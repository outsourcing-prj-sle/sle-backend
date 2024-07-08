package egovframework.example.user.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import egovframework.example.admin.service.AdminUserListDTO;
import egovframework.example.admin.service.AdminUserVO;
import egovframework.example.cmmn.service.LoginVO;
import egovframework.example.cmmn.service.SurveyVO;
import egovframework.example.naver.dto.GneInfoDto;
import egovframework.example.naver.dto.GneUserDto;
import egovframework.example.naver.dto.NaverUserDto;
import egovframework.example.naver.service.NaverService;
import egovframework.example.user.dto.ClassPersonalityDTO;
import egovframework.example.user.dto.IdttLTResultDTO;
import egovframework.example.user.service.IdTokTokVO;
import egovframework.example.user.service.MySelVO;
import egovframework.example.user.dto.StudentsDTO;
import egovframework.example.user.service.UserManageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service("userManageService")
public class UserManageServiceImpl implements UserManageService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserManageServiceImpl.class);

	private static final String apiKey = "pCc9mplHYCQ0xsRfrJSB2tUEIRPGGZkw";

	@Resource(name = "naverServiceImpl")
	private NaverService naverService;

	@Resource(name = "userManageMapper")
	private UserManageMapper mapper;

	/**
	 * 회원정보 조회
	 *
	 * @return LoginVO
	 */
	@Override
	public LoginVO selectUserInfo(LoginVO loginVO) {

		return mapper.selectUserInfo(loginVO);
	}

	/**
	 * 토큰으로 회원 인증
	 * @return boolean
	 */
	public boolean authorizationUser(LoginVO loginVO) {
		return mapper.authorizationUser(loginVO);
	}

	/**
	 * 회원정보 등록
	 */
	@Override
	public LoginVO insertUserInfo(LoginVO loginVO) {
		if(mapper.authorizationUser(loginVO)) {
			// 기존 사용자
			mapper.updateUserInfo(loginVO);
		} else {
			// 신규 사용자
			mapper.insertUserInfo(loginVO);
			mapper.insertUserScrtyEstbs(loginVO);
		}

		return mapper.selectUserInfo(loginVO);
	}

	/**
	 * 회원정보 수정
	 */
	@Override
	public void updateUserInfo(LoginVO loginVO) {
		mapper.updateUserInfo(loginVO);
	}

	/**
	 * 회원정보 수정 상세(성별, 생년월일)
	 */
	@Override
	public void updateUserInfoDtl(LoginVO loginVO) {
		mapper.updateUserInfoDtl(loginVO);
	}

	/**
	 * 학생 SEL 목록 조회
	 */
	@Override
	public List<StudentsDTO> selectStudentSelList(LoginVO loginVO) {

		return mapper.selectStudentSelList(loginVO);
	}

	/**
	 * 선생님 SEL 목록 조회
	 */
	@Override
	public List<MySelVO> selectTeacherSelList(LoginVO loginVO) {

		return mapper.selectTeacherSelList(loginVO);
	}

	/**
	 * 선생님 권한 사용자 체크
	 */
	@Override
	public Boolean isReallyTeacher(LoginVO loginVO) {

		return mapper.isReallyTeacher(loginVO);
	}

	/**
	 * 선생님 권한 사용자 체크 상세
	 */
	@Override
	public LoginVO isReallyTeacherDtl(LoginVO loginVO) {
		return mapper.isReallyTeacherDtl(loginVO);
	}

	/**
	 * 아이디 톡톡 선생님 조회
	 */
	@Override
	public List<IdTokTokVO> selectIdTokTokTeacher(LoginVO loginVO) {
		return mapper.selectIdTokTokTeacher(loginVO);
	}

	/**
	 * 평가 결과 저장
	 */
	@Override
	public void insertResearchResult(SurveyVO surveyVO) {
		// 기록 검색 -> insert/update
		if(mapper.selectSurveyHistoryisExist(surveyVO)) {
			mapper.updateResearchResult(surveyVO);
		} else {
			mapper.insertResearchResult(surveyVO);
		}
	}

	/**
	 * Gne 사용자 정보 입력
	 * @param loginVO
	 */
	@Override
	public void updateGneUserInfo(LoginVO loginVO) {
		NaverUserDto naverUserDto = NaverUserDto.builder()
				.primaryEmail(loginVO.getUserEmail())
				.build();

		GneInfoDto<GneUserDto> gneUserDto = naverService.procGneUserInfo(naverUserDto);

		if(!gneUserDto.isSuccess()) {
			throw new RuntimeException("경남교육청 사용자 정보 조회에 실패하였습니다.");
		}

		LoginVO vo = LoginVO.builder()
				.authorization(gneUserDto.getData().getUserId())
				.name(gneUserDto.getData().getUserNm())
				.schulGradeCode(gneUserDto.getData().getSchulGradeCode())
				.stYear(gneUserDto.getData().getStdrYear())
				.gradeNm(gneUserDto.getData().getStGrade())
				.classCode(gneUserDto.getData().getStClass())
				.classNm(gneUserDto.getData().getStClassNm())
				.stNumber(gneUserDto.getData().getStNumber())
				.userType(gneUserDto.getData().getUserSeCode())
				.userSpaceInfo(gneUserDto.getData().getSchulNm())
				.schulCode(gneUserDto.getData().getSchulCode())
				.schulGradeCode(gneUserDto.getData().getSchulGradeCode())
				.build();

		mapper.updateGneUserInfo(vo);
	}

	/**
	 * 학습성향톡톡 API 조회
	 * @param loginVO
	 * @return
	 */
	@Override
	public IdttLTResultDTO<ClassPersonalityDTO> selectIdttLT(LoginVO loginVO, String id) {

		LoginVO vo = mapper.selectUserInfo(loginVO);

		String baseUrl = "https://smile-second-store.itt.link:4555/gne-api/folio/v3.1/analysis-result";

		try {
			URI uriUserPersonality = new URI(baseUrl +
					"?apiKey=" + apiKey +
					"&analysisId=folio-instructor-user-personality" +
					"&schoolId=" + vo.getSchulCode() +
//					"&grade=" + vo.getGradeNm() +
					"&grade=" + 1 +
					"&schoolYear=" + vo.getStYear() +
//					"&classNo=" + vo.getClassCode() +
					"&classNo=" + 1 +
//					"&learnerId=" + id
					"&learnerId=" + "tlqjach-stu101@gne.go.kr"
			);

			URI uriClassPersonality = new URI(baseUrl +
					"?apiKey=" + apiKey +
					"&analysisId=folio-instructor-class-personality" +
					"&schoolId=" + vo.getSchulCode() +
					"&grade=" + vo.getGradeNm() +
					"&schoolYear=" + vo.getStYear() +
					"&classNo=" + vo.getClassCode()
			);

			RestTemplate restTemplate = new RestTemplate();
			restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
			restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
				@Override
				public boolean hasError(ClientHttpResponse response) throws IOException {
					HttpStatus statusCode = response.getStatusCode();
					return statusCode.series() == HttpStatus.Series.SERVER_ERROR;
				}
			});

			HttpHeaders headers = new HttpHeaders();
			HttpEntity<String> entity = new HttpEntity<>(headers);

			IdttLTResultDTO<ClassPersonalityDTO> dto = new IdttLTResultDTO();

			ResponseEntity<String> responseEntityUser = restTemplate.exchange(uriUserPersonality, HttpMethod.GET, entity, String.class);
			if (responseEntityUser.getStatusCode() == HttpStatus.OK) {
				ObjectMapper mapper = new ObjectMapper();
				mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
				mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
				mapper.enable(JsonReadFeature.ALLOW_NON_NUMERIC_NUMBERS.mappedFeature());

				try {
					ArrayList<HashMap<String, String>> userPersonalityList = mapper.readValue(
							responseEntityUser.getBody(),
							new TypeReference<ArrayList<HashMap<String, String>>>() {}
					);
					dto.setUserPersonality(userPersonalityList);
				} catch (JsonProcessingException e) {
					LOGGER.error("학습성향 학생별 점수 수집에 실패하였습니다.", e.getMessage(), e);
					throw new RuntimeException("학습성향 학생별 점수 수집에 실패하였습니다.", e);
				}
			} else {
				LOGGER.error("학습성향 학생별 점수 API 접속에 실패하였습니다.", responseEntityUser.getBody());
				throw new RuntimeException("학습성향 학생별 점수 API 접속에 실패하였습니다.");
			}

			ResponseEntity<String> responseEntityClass = restTemplate.exchange(uriClassPersonality, HttpMethod.GET, entity, String.class);
			if (responseEntityClass.getStatusCode() == HttpStatus.OK) {
				ObjectMapper mapper = new ObjectMapper();
				mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
				mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
				mapper.enable(JsonReadFeature.ALLOW_NON_NUMERIC_NUMBERS.mappedFeature());

				try {
					ArrayList<ClassPersonalityDTO> classPersonalityList = mapper.readValue(
							responseEntityClass.getBody(),
							new TypeReference<ArrayList<ClassPersonalityDTO>>() {}
					);
					dto.setClassPersonality(classPersonalityList);
				} catch (JsonProcessingException e) {
					LOGGER.error("학습성향 학급별 점수 수집에 실패하였습니다.", e.getMessage(), e);
					throw new RuntimeException("학습성향 학급별 점수 수집에 실패하였습니다.", e);
				}
			} else {
				LOGGER.error("학습성향 학급별 점수 API 접속에 실패하였습니다.", responseEntityClass.getBody());
				throw new RuntimeException("학습성향 학급별 점수 API 접속에 실패하였습니다.");
			}

			return dto;
		} catch (URISyntaxException e) {
			LOGGER.error(e.getMessage());
			throw new RuntimeException("URI 생성에 실패하셨습니다.", e);
		}
	}
	
	/**
	 * 유저 전체/검색 조회
	 */
	@Override
	public AdminUserListDTO selectUserByConditions(AdminUserVO adminUserVO){
		return AdminUserListDTO.builder()
				.userInfoList(mapper.selectUserByConditions(adminUserVO))
				.pageNo(adminUserVO.getPageNo())
				.limit(adminUserVO.getLimit())
				.totalCount(adminUserVO.getTotalCount())
				.build();
	}
}
