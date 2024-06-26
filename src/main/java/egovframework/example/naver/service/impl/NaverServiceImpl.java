/*
 * Copyright 2008-2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package egovframework.example.naver.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import egovframework.example.naver.dto.*;
import egovframework.example.naver.service.NaverService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * @Class Name : NaverServiceImpl.java
 * @Description : 웨일 스페이스 연동 및 경남교육청 연동 관련 서비스 구현
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2024.06.21  SON         최초생성
 *
 * @author
 * @since 2024.06.21
 */
@Slf4j
@Service
@Transactional
public class NaverServiceImpl implements NaverService {

    private final String NAVER_CLIENT_ID = "iymVHli6FerqTAKqTnNV";
    private final String CHANGE_GOOGLE_CLIENT_SECRET = "HoUGkLMMj6";
    private final String API_KEY = "iURgyQpvBeuPwgyh8zEsxd79rVtc93dy0QeXkyMieMuiH3Ro7Bp9qUOdDt4T5M9A";

    /**
     * 웨일스페이스 억세스토큰 발행
     */
    @Override
    public NaverTokenDto procAccessToken(NaverCodeDto naverCodeDto) {
        // PARAM
        String authorizationCode = naverCodeDto.getCode();
        String state             = naverCodeDto.getState();

        // MultiValueMap 생성
        MultiValueMap<String, String> params= new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", NAVER_CLIENT_ID);
        params.add("client_secret", CHANGE_GOOGLE_CLIENT_SECRET);
        params.add("code", authorizationCode);
        params.add("state", state);

        RestTemplate restTemplate = new RestTemplate();
        // 에러 핸들링
        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
            public boolean hasError(ClientHttpResponse response) throws IOException {
                HttpStatus statusCode = response.getStatusCode();
                return statusCode.series() == HttpStatus.Series.SERVER_ERROR;
            }
        });

        // 요청 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        String url = "https://auth.whalespace.io/oauth2/v1.1/token";
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, new HttpEntity<>(params, headers), String.class);
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            log.info("{}", responseEntity.getBody());

            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); // 이 설정을 통해 JSON의 모든 데이터를 파싱하는 것이 아닌 내가 필요로 하는 데이터, 즉 내가 필드로 선언한 데이터들만 파싱할 수 있다.
            mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
            mapper.enable(JsonReadFeature.ALLOW_NON_NUMERIC_NUMBERS.mappedFeature());

            NaverTokenDto naverTokenDto = null;
            try {
                naverTokenDto = mapper.readValue(responseEntity.getBody(), new TypeReference<NaverTokenDto>() {
                    @Override
                    public Type getType() {
                        return super.getType();
                    }
                });
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            return naverTokenDto;
        }
        else {
            log.error("{}", responseEntity.getBody());
            throw new RuntimeException("네이버 엑세스 토큰 발행에 실패하였습니다.");
        }
    }

    @Override
    public NaverUserDto procUserInfo(NaverTokenDto naverTokenDto) {
        // PARAM
        String accessToken = naverTokenDto.getAccess_token();

        RestTemplate restTemplate = new RestTemplate();
        // 에러 핸들링
        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
            public boolean hasError(ClientHttpResponse response) throws IOException {
                HttpStatus statusCode = response.getStatusCode();
                return statusCode.series() == HttpStatus.Series.SERVER_ERROR;
            }
        });

        // 요청 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("Authorization", "Bearer ".concat(accessToken));

        String url = "https://api.whalespace.io/oauth2/v1.1/userinfo";
        HttpEntity request = new HttpEntity(headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            log.info("{}", responseEntity.getBody());

            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); // 이 설정을 통해 JSON의 모든 데이터를 파싱하는 것이 아닌 내가 필요로 하는 데이터, 즉 내가 필드로 선언한 데이터들만 파싱할 수 있다.
            mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
            mapper.enable(JsonReadFeature.ALLOW_NON_NUMERIC_NUMBERS.mappedFeature());

            NaverUserDto naverUserDto = null;
            try {
                naverUserDto = mapper.readValue(responseEntity.getBody(), new TypeReference<NaverUserDto>() {
                    @Override
                    public Type getType() {
                        return super.getType();
                    }
                });
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            return naverUserDto;
        }
        else {
            log.error("{}", responseEntity.getBody());
            throw new RuntimeException("네이버 사용자정보 조회에 실패하였습니다.");
        }
    }

    @Override
    public GneInfoDto<GneUserDto> procGneUserInfo(NaverUserDto naverUserDto) {
        // PARAM
        String primaryEmail = naverUserDto.getPrimaryEmail();

        RestTemplate restTemplate = new RestTemplate();
        // 에러 핸들링
        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
            public boolean hasError(ClientHttpResponse response) throws IOException {
                HttpStatus statusCode = response.getStatusCode();
                return statusCode.series() == HttpStatus.Series.SERVER_ERROR;
            }
        });

        // 요청 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("api-key", API_KEY);

        String url = "https://devnewtab.itt.link".concat("/api/user/userInfo.do").concat("?userId=").concat(primaryEmail).concat("&stdrYear=");
        HttpEntity request = new HttpEntity(null, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            log.info("{}", responseEntity.getBody());

            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); // 이 설정을 통해 JSON의 모든 데이터를 파싱하는 것이 아닌 내가 필요로 하는 데이터, 즉 내가 필드로 선언한 데이터들만 파싱할 수 있다.
            mapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true); // String "" -> null
            mapper.enable(JsonReadFeature.ALLOW_NON_NUMERIC_NUMBERS.mappedFeature());

            GneInfoDto<GneUserDto> gneDto = null;
            try {
                gneDto = mapper.readValue(responseEntity.getBody(), new TypeReference<GneInfoDto<GneUserDto>>() {
                    @Override
                    public Type getType() {
                        return super.getType();
                    }
                });
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            return gneDto;
        }
        else {
            log.error("{}", responseEntity.getBody());
            throw new RuntimeException("경남교육청 사용자 정보 조회에 실패하였습니다.");
        }
    }

    @Override
    public GneListDto<GneSchoolUserDto> procGneSchoolUserInfo(GneInfoDto<GneUserDto> gneUserDto) {
        // PARAM
        String schulCode = gneUserDto.getData().getSchulCode(); // 학교코드
        String stGrade   = gneUserDto.getData().getStGrade(); // 학년
        String stClass   = gneUserDto.getData().getStClass(); // 학년

        RestTemplate restTemplate = new RestTemplate();
        // 에러 핸들링
        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
            public boolean hasError(ClientHttpResponse response) throws IOException {
                HttpStatus statusCode = response.getStatusCode();
                return statusCode.series() == HttpStatus.Series.SERVER_ERROR;
            }
        });

        // 요청 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("api-key", API_KEY);

        String url = "https://devnewtab.itt.link".concat("/api/user/schulUserList.do").concat("?schulCode=").concat(schulCode).concat("&userSeCode=").concat("04").concat("&stGrade=").concat(stGrade).concat("&stClass=").concat(stClass);
        HttpEntity request = new HttpEntity(null, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            log.info("{}", responseEntity.getBody());

            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); // 이 설정을 통해 JSON의 모든 데이터를 파싱하는 것이 아닌 내가 필요로 하는 데이터, 즉 내가 필드로 선언한 데이터들만 파싱할 수 있다.
            mapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true); // String "" -> null
            mapper.enable(JsonReadFeature.ALLOW_NON_NUMERIC_NUMBERS.mappedFeature());

            GneListDto<GneSchoolUserDto> gneDto = null;
            try {
                gneDto = mapper.readValue(responseEntity.getBody(), new TypeReference<GneListDto<GneSchoolUserDto>>() {
                    @Override
                    public Type getType() {
                        return super.getType();
                    }
                });
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            return gneDto;
        }
        else {
            log.error("{}", responseEntity.getBody());
            throw new RuntimeException("경남교육청 학교별 사용자 목록 조회에 실패하였습니다.");
        }
    }
}
