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
package egovframework.example.naver.service;

import egovframework.example.naver.dto.*;

import java.util.List;

/**
 * @Class Name : NaverService.java
 * @Description : 웨일 스페이스 연동 및 경남교육청 연동 관련 서비스
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2024.06.21  SON         최초생성
 *
 * @author
 * @since 2024.06.21
 */
public interface NaverService {

    /**
     * 네이버 인증코드로 억세스토큰 발급
     * @param naverCodeDto
     * @return
     */
    NaverTokenDto procAccessToken(NaverCodeDto naverCodeDto);

    /**
     * 네이버 억세스토큰으로 사용자정보 조회
     * @param naverTokenDto
     * @return
     */
    NaverUserDto procUserInfo(NaverTokenDto naverTokenDto);

    /**
     * 네이버 사용자정보로 경남교육청 사용자정보 조회
     * @param naverUserDto
     * @return
     */
    GneInfoDto<GneUserDto> procGneUserInfo(NaverUserDto naverUserDto);

    /**
     * 네이버 사용자정보로 경남교육청 학교별 사용자 목록 조회
     * @param gneUserDto
     * @return
     */
    GneListDto<GneSchoolUserDto> procGneSchoolUserInfo(GneInfoDto<GneUserDto> gneUserDto);

    /**
     * 경남교육청 학교정보 조회
     * @return
     */
    GneInfoDto<List<GneSchulDTO>> procGneSchulInfo();


    /**
     * 네이버 사용자정보로 경남교육청 학교별 사용자 목록 조회
     * @param gneUserDto
     * @return
     */
    GneListDto<GneSchoolUserDto> procGneSchoolTeacherInfo(GneInfoDto<GneUserDto> gneUserDto);
}
