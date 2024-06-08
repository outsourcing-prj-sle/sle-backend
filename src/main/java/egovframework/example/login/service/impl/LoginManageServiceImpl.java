package egovframework.example.login.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.example.login.service.LoginManageService;

@Service("loginManageService")
public class LoginManageServiceImpl implements LoginManageService {
	
	@Resource(name = "loginManageMapper")
	private LoginManageMapper loginManageMapper;
	
	
}
