package egovframework.example.login.service;

import egovframework.example.cmmn.service.LoginVO;

public interface LoginManageService {
	
	/**
	 * 최초 회원 가입 시 필수 제공 정보 동의
	 */
	void updateRequiredAgreeInfo(LoginVO loginVO);
	
}
