package egovframework.example.admin.login.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.example.admin.login.service.AdminLoginService;
import egovframework.example.cmmn.service.AccountDTO;
import egovframework.example.cmmn.service.AdminLoginVO;

@Service("adminLoginService")
public class AdminLoginServiceImpl implements AdminLoginService {
	
	@Resource(name = "adminLoginMapper")
	private AdminLoginMapper mapper;
	
	@Override
	public AdminLoginVO selectUserById(AccountDTO accountDTO) {
		return mapper.selectUserById(accountDTO);
	}
}
