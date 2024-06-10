package egovframework.example.user.service;

import egovframework.example.cmmn.service.LoginVO;

public interface UserManageService {
	
	/**
	 * 회원정보 조회
	 * @return LoginVO 
	 */
	LoginVO selectUserInfo(LoginVO loginVO);
	
	/**
	 * 회원정보 등록
	 */
	void insertUserInfo(LoginVO loginVO);
	
	/**
	 * 회원정보 수정
	 */
	void updateUserInfo(LoginVO loginVO);
}
