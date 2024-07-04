package egovframework.example.admin.service;

import java.util.List;
import java.util.Map;

import egovframework.example.cmmn.service.AdminLoginVO;

public interface AdminManageService {
	
	String insertUser(AdminLoginVO AdminLoginVO);
	
	AdminLoginVO selectUser(AdminLoginVO AdminLoginVO);
	
	List<AdminLoginVO> selectUserAll(Map<String, String> conditional);
	
	String deleteUser(String id);
	
	boolean checkUserById(String id);
	
	boolean authorizationUser(String id);
}
