package egovframework.example.user.service.impl;

import java.util.HashMap;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import egovframework.example.user.service.UserManageVO;

@Mapper("userManageMapper")
public interface UserManageMapper {
	
	/**
	 * 회원정보 조회
	 * @return UserManageVO 
	 */
	UserManageVO selectUserInfo();
}
