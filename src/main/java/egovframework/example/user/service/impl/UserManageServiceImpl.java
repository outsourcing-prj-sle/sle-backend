package egovframework.example.user.service.impl;

import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.example.user.service.UserManageService;
import egovframework.example.user.service.UserManageVO;

@Service("userManageService")
public class UserManageServiceImpl implements UserManageService {
	
	@Resource(name="userManageMapper")
	private UserManageMapper mapper;
	
	/**
	 * 회원정보 조회
	 * @return UserManageVO 
	 */
	@Override
	public UserManageVO selectUserInfo() {
		
		return mapper.selectUserInfo();
	}

}
