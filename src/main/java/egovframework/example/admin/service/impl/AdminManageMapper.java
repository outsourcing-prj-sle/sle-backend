package egovframework.example.admin.service.impl;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import egovframework.example.cmmn.service.AdminLoginVO;

@Mapper("adminManageMapper")
public interface AdminManageMapper {

	void insertUser(AdminLoginVO AdminLoginVO);
	
	AdminLoginVO selectUser(AdminLoginVO AdminLoginVO);
	
	List<AdminLoginVO> selectUserAll(Map<String, String> conditional);
	
	void updateUser(AdminLoginVO AdminLoginVO);
	
	void deleteUser(String id);
	
	boolean checkUserById(String id);
	
	boolean checkUserByUniqId(String uniqId);

	boolean authorizationUser(String id);
}
