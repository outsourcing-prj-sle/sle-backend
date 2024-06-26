package egovframework.example.user.service.impl;

import egovframework.example.cmmn.service.LoginVO;
import egovframework.example.cmmn.service.SurveyVO;
import egovframework.example.user.service.IdTokTokVO;
import egovframework.example.user.service.MySelVO;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import java.util.List;

@Mapper("userManageMapper")
public interface UserManageMapper {
	
	/**
	 * 회원정보 조회
	 * @return LoginVO 
	 */
	LoginVO selectUserInfo(LoginVO loginVO);
	
	/**
	 * ID로 회원 정보 조회
	 * @return boolean
	 */
	boolean checkUserById(LoginVO loginVO);
	
	/**
	 * 토큰으로 회원 인증
	 * @return boolean
	 */
	boolean authorizationUser(LoginVO loginVO);
	
	/**
	 * 회원정보 등록
	 */
	void insertUserInfo(LoginVO loginVO);
	
	/**
	 * 사용자보안설정 등록
	 */
	void insertUserScrtyEstbs(LoginVO loginVO);
	
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
	List<MySelVO> selectStudentSelList(LoginVO loginVO);
	
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
	LoginVO isReallyTeacherDtl(String uniqId);
	
	/**
	 * 아이디 톡톡 선생님 조회
	 */
	List<IdTokTokVO> selectIdTokTokTeacher(LoginVO loginVO);
	
	/**
	 * 평가 결과 유무 조회
	 */
	Boolean selectSurveyHistoryisExist(SurveyVO surveyVO);
	
	/**
	 * 평가 결과 수정
	 */
	void updateResearchResult(SurveyVO surveyVO);
	
	/**
	 * 평가 결과 저장
	 */
	void insertResearchResult(SurveyVO surveyVO);

	/**
	 * 아이디로 사용자 UNIQ_ID 조회
	 * @param loginVO
	 * @return
	 */
	String selectUniqId(LoginVO loginVO);
}
