package egovframework.example.user.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.example.cmmn.service.LoginVO;
import egovframework.example.user.service.UserManageService;

@Service("userManageService")
public class UserManageServiceImpl implements UserManageService {
	
	@Resource(name="userManageMapper")
	private UserManageMapper mapper;
	
	/**
	 * 회원정보 조회
	 * @return LoginVO 
	 */
	@Override
	public LoginVO selectUserInfo(LoginVO loginVO) {
		
		return mapper.selectUserInfo(loginVO);
	}

	
	/**
	 * 회원정보 등록
	 */
	@Override
	public void insertUserInfo(LoginVO loginVO) {
		mapper.insertUserInfo(loginVO);
		mapper.insertUserScrtyEstbs(loginVO);
	}

	/**
	 * 회원정보 수정
	 */
	@Override
	public void updateUserInfo(LoginVO loginVO) {
		mapper.updateUserInfo(loginVO);
	}

}
