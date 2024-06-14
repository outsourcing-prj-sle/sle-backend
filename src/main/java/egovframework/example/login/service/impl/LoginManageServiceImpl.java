package egovframework.example.login.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.example.cmmn.service.AccountDTO;
import egovframework.example.cmmn.service.LoginVO;
import egovframework.example.login.service.LoginManageService;

@Service("loginManageService")
public class LoginManageServiceImpl implements LoginManageService {
	
	@Resource(name = "loginManageMapper")
	private LoginManageMapper loginManageMapper;

	/**
	 * 최초 회원 가입 시 필수 제공 정보 동의
	 */
	@Override
	public void updateRequiredAgreeInfo(LoginVO loginVO) {
		loginManageMapper.updateRequiredAgreeInfo(loginVO);
	}
	
	/**
	 * 로그인시 ID로 유저 찾기
	 * @return LoginVO
	 */	
	@Override
	public LoginVO selectUserById(AccountDTO accountVO){
		return loginManageMapper.selectUserById(accountVO);
	}
	
}
