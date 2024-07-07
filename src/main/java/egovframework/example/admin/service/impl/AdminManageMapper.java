package egovframework.example.admin.service.impl;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import egovframework.example.admin.service.AdminUserVO;
import egovframework.example.cmmn.service.AdminLoginVO;

@Mapper("adminManageMapper")
public interface AdminManageMapper {

	void insertUser(AdminLoginVO AdminLoginVO);
	
	AdminLoginVO selectUser(AdminLoginVO AdminLoginVO);
	
	List<AdminLoginVO> selectUserAll(AdminUserVO adminUserVO);
	
	void updateUser(AdminLoginVO AdminLoginVO);
	
	void deleteUser(String id);
	
	boolean checkUserById(String id);
	
	boolean checkUserByUniqId(String uniqId);

	boolean authorizationUser(String id);
	
	int selectAdminUserInfoCount(AdminUserVO user);
}
