package egovframework.example.admin.service;

import java.util.List;

import egovframework.example.cmmn.service.AdminLoginVO;

public interface AdminManageService {
	
	String insertUser(AdminLoginVO AdminLoginVO);
	
	AdminLoginVO selectUser(AdminLoginVO AdminLoginVO);
	
	List<AdminLoginVO> selectUserAll(String role);
	
	String deleteUser(String id);
	
	boolean checkUserById(String id);
	
	boolean authorizationUser(String id);
}
