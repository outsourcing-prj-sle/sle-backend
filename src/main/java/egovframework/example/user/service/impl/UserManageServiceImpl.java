package egovframework.example.user.service.impl;

import egovframework.example.cmmn.service.LoginVO;
import egovframework.example.cmmn.service.SurveyVO;
import egovframework.example.user.service.IdTokTokVO;
import egovframework.example.user.service.MySelVO;
import egovframework.example.user.dto.StudentsDTO;
import egovframework.example.user.service.UserManageService;
import org.egovframe.rte.fdl.cmmn.exception.FdlException;
import org.egovframe.rte.fdl.idgnr.EgovIdGnrService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("userManageService")
public class UserManageServiceImpl implements UserManageService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserManageServiceImpl.class);

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
	 * ID로 회원 정보 조회
	 * @return boolean
	 */
	public boolean checkUserById(LoginVO loginVO) {
		return mapper.checkUserById(loginVO);
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
		if(mapper.checkUserById(loginVO)) {
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
}
