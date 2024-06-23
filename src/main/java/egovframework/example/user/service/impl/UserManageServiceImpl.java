package egovframework.example.user.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.egovframe.rte.fdl.cmmn.exception.FdlException;
import org.egovframe.rte.fdl.idgnr.EgovIdGnrService;
import org.springframework.stereotype.Service;

import egovframework.example.cmmn.service.LoginVO;
import egovframework.example.cmmn.service.SurveyVO;
import egovframework.example.user.service.IdTokTokVO;
import egovframework.example.user.service.MySelVO;
import egovframework.example.user.service.UserManageService;

@Service("userManageService")
public class UserManageServiceImpl implements UserManageService {

	@Resource(name = "userManageMapper")
	private UserManageMapper mapper;
	
	@Resource(name = "userIdGnrService")
	private EgovIdGnrService userIdGnrService;

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
	public boolean checkUserById(String id) {
		return mapper.checkUserById(id);
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
	public String insertUserInfo(LoginVO loginVO) {
		try {
			if(mapper.checkUserById(loginVO.getId())) {
				loginVO.setUniqId(mapper.selectUniqId(loginVO));
				mapper.updateUserInfo(loginVO);
			} else {
				loginVO.setUniqId(userIdGnrService.getNextStringId());
				loginVO.isRole();

				mapper.insertUserInfo(loginVO);
				mapper.insertUserScrtyEstbs(loginVO);
			}
		} catch (FdlException e) {
			e.printStackTrace();
		}

		return loginVO.getUniqId();
	}

	/**
	 * 회원정보 수정
	 */
	@Override
	public void updateUserInfo(LoginVO loginVO) {
		loginVO.setUniqId(mapper.selectUniqId(loginVO));
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
	public List<MySelVO> selectStudentSelList(LoginVO loginVO) {
		
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
	public Boolean isReallyTeacher(String uniqId) {
		
		return mapper.isReallyTeacher(uniqId);
	}

	/**
	 * 선생님 권한 사용자 체크 상세
	 */
	@Override
	public LoginVO isReallyTeacherDtl(String uniqId) {
		return mapper.isReallyTeacherDtl(uniqId);
	}
	
	/**
	 * 아이디 톡톡 선생님 조회
	 */
	@Override
	public List<IdTokTokVO> selectIdTokTokTeacher(LoginVO loginVO) {
		return mapper.selectIdTokTokTeacher(loginVO);
	}

	/**
	 * 평가 결과 유무 조회
	 */
	@Override
	public Boolean selectSurveyHistoryisExist(SurveyVO surveyVO) {
		return mapper.selectSurveyHistoryisExist(surveyVO);
	}

	/**
	 * 평가 결과 수정
	 */
	@Override
	public void updateResearchResult(SurveyVO surveyVO) {
		mapper.updateResearchResult(surveyVO);
	}

	/**
	 * 평가 결과 저장
	 */
	@Override
	public void insertResearchResult(SurveyVO surveyVO) {
		mapper.insertResearchResult(surveyVO);
	}
}
