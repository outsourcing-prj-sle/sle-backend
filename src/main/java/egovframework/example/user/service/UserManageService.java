package egovframework.example.user.service;

import egovframework.example.admin.service.AdminUserListDTO;
import egovframework.example.admin.service.AdminUserVO;
import egovframework.example.cmmn.service.AdminLoginVO;
import egovframework.example.cmmn.service.LoginVO;
import egovframework.example.cmmn.service.SurveyVO;
import egovframework.example.user.dto.IdttLTResultDTO;
import egovframework.example.user.dto.StudentsDTO;

import java.util.List;
import java.util.Map;

public interface UserManageService {
	
	/**
	 * 회원정보 조회
	 */
	LoginVO selectUserInfo(LoginVO loginVO);
	
	/**
	 * 토큰으로 회원 인증
	 * @return boolean
	 */
	boolean authorizationUser(LoginVO loginVO);
	
	/**
	 * 회원정보 등록
	 */
	LoginVO insertUserInfo(LoginVO loginVO);
	
	/**
	 * 회원정보 수정
	 */
	void updateUserInfo(LoginVO loginVO);

	/**
	 * 회원정보 수정 상세(성별, 생년월일)
	 */
	void updateUserInfoDtl(LoginVO loginVO);
	
	/**
	 * 학생 SEL 목록 조회
	 */
	List<StudentsDTO> selectStudentSelList(LoginVO loginVO);
	
	/**
	 * 선생님 SEL 목록 조회
	 */
	List<MySelVO> selectTeacherSelList(LoginVO loginVO);
	
	/**
	 * 선생님 권한 사용자 체크
	 */
	Boolean isReallyTeacher(LoginVO loginVO);
	
	/**
	 * 선생님 권한 사용자 체크 상세
	 */
	LoginVO isReallyTeacherDtl(LoginVO loginVO);
	
	/**
	 * 아이디 톡톡 선생님 조회
	 */
	List<IdTokTokVO> selectIdTokTokTeacher(LoginVO loginVO);

	/**
	 * 평가 결과 저장
	 */
	void insertResearchResult(SurveyVO surveyVO);


	/**
	 * Gne 사용자 정보 입력
	 * @param loginVO
	 */
	void updateGneUserInfo(LoginVO loginVO);

	/**
	 * 학습성향톡톡 API 조회
	 * @param loginVO
	 * @return
	 */
	IdttLTResultDTO selectIdttLT(LoginVO loginVO, String id);
	
	
	/**
	 * 유저 전체/검색 조회
	 */
	AdminUserListDTO selectUserByConditions(AdminUserVO adminUserVO);
}
