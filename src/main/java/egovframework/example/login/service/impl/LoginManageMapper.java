package egovframework.example.login.service.impl;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import egovframework.example.cmmn.service.AccountDTO;
import egovframework.example.cmmn.service.LoginVO;

@Mapper("loginManageMapper")
public interface LoginManageMapper {
	
	/**
	 * 최초 회원 가입 시 필수 제공 정보 동의
	 */
	void updateRequiredAgreeInfo(LoginVO loginVO);
	/**
	 * 로그인시 ID로 유저 찾기
	 * @return LoginVO
	 */
	LoginVO selectUserById(AccountDTO accountDTO);
}
