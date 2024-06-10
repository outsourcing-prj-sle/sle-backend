package egovframework.example.user.service.impl;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import egovframework.example.cmmn.service.LoginVO;

@Mapper("userManageMapper")
public interface UserManageMapper {
	
	/**
	 * 회원정보 조회
	 * @return UserManageVO 
	 */
	LoginVO selectUserInfo(LoginVO loginVO);
	
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
}
