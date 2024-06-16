package egovframework.example.user.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.egovframe.rte.fdl.cmmn.exception.FdlException;
import org.egovframe.rte.fdl.idgnr.EgovIdGnrService;
import org.springframework.stereotype.Service;

import egovframework.example.cmmn.service.LoginVO;
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
	public void insertUserInfo(LoginVO loginVO) {
		try {
			loginVO.setUniqId(userIdGnrService.getNextStringId());
			loginVO.isRole();
			mapper.insertUserInfo(loginVO);
			mapper.insertUserScrtyEstbs(loginVO);
		} catch (FdlException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 회원정보 수정
	 */
	@Override
	public void updateUserInfo(LoginVO loginVO) {
		mapper.updateUserInfo(loginVO);
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
}
