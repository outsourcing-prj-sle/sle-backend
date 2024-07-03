package egovframework.example.admin.login.service.impl;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import egovframework.example.cmmn.service.AccountDTO;
import egovframework.example.cmmn.service.AdminLoginVO;

@Mapper("adminLoginMapper")
public interface AdminLoginMapper {
	AdminLoginVO selectUserById(AccountDTO accountDTO);
}
