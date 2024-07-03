package egovframework.example.admin.login.service;

import egovframework.example.cmmn.service.AccountDTO;
import egovframework.example.cmmn.service.AdminLoginVO;

public interface AdminLoginService {
	AdminLoginVO selectUserById(AccountDTO accountDTO);
}
