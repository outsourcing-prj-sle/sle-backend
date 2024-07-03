package egovframework.example.admin.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.egovframe.rte.fdl.cmmn.exception.FdlException;
import org.egovframe.rte.fdl.idgnr.EgovIdGnrService;
import org.springframework.stereotype.Service;

import egovframework.example.admin.service.AdminManageService;
import egovframework.example.cmmn.service.AdminLoginVO;

@Service("adminManageService")
public class AdminManageServiceImpl implements AdminManageService {
	
	@Resource(name = "adminManageMapper")
	private AdminManageMapper mapper;
	
	@Resource(name = "adminUserIdGnrService")
	private EgovIdGnrService adminUserIdGnrService;

	@Override
	public AdminLoginVO selectUser(AdminLoginVO AdminLoginVO) {
		return mapper.selectUser(AdminLoginVO);
	}
	
	@Override
	public String insertUser(AdminLoginVO AdminLoginVO) {
		try {
			if(mapper.checkUserByUniqId(AdminLoginVO.getUniqId())) {
				mapper.updateUser(AdminLoginVO);
			} else {
				AdminLoginVO.setUniqId(adminUserIdGnrService.getNextStringId());
				mapper.insertUser(AdminLoginVO);
			}
		} catch (FdlException e) {
			e.printStackTrace();
		}

		return AdminLoginVO.getUniqId();
	}
	
	@Override
	public List<AdminLoginVO> selectUserAll(String role){
		return mapper.selectUserAll(role);
	}
	
	@Override
	public String deleteUser(String id){
		if(mapper.checkUserById(id)) {
			mapper.deleteUser(id);
			return id;
		}
		return "없는 유저 입니다";
	}

	@Override
	public boolean checkUserById(String id) {
		return mapper.checkUserById(id);
	}

	@Override
	public boolean authorizationUser(String id) {
		return mapper.authorizationUser(id);
	}
                               
}
