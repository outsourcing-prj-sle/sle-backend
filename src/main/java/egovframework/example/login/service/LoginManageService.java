package egovframework.example.login.service;

import egovframework.example.cmmn.service.AccountDTO;
import egovframework.example.cmmn.service.LoginVO;

public interface LoginManageService {

	/**
	 * 최초 회원 가입 시 필수 제공 정보 동의
	 */
	void updateRequiredAgreeInfo(LoginVO loginVO);
	/**
	 * 로그인시 ID로 유저 찾기
	 * @return LoginVO
	 */	
	LoginVO selectUserById(AccountDTO accountVO);
	
	/**
	 * 헤더 토큰으로 유저 정보 호출
	 * @return LoginVO
	 */		
	LoginVO authorizationUser(String authorization);
	
}
