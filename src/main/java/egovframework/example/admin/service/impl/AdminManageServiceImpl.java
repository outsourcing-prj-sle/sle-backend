package egovframework.example.admin.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.egovframe.rte.fdl.cmmn.exception.FdlException;
import org.egovframe.rte.fdl.idgnr.EgovIdGnrService;
import org.springframework.stereotype.Service;

import egovframework.example.admin.service.AdminManageService;
import egovframework.example.admin.service.AdminUserListDTO;
import egovframework.example.admin.service.AdminUserVO;
import egovframework.example.admin.system.model.SiteListDTO;
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
	public AdminUserListDTO selectUserAll(AdminUserVO adminUserVO){
        return AdminUserListDTO.builder()
                .adminUserInfoList(mapper.selectUserAll(adminUserVO))
                .pageNo(adminUserVO.getPageNo())
                .limit(adminUserVO.getLimit())
                .totalCount(mapper.selectAdminUserInfoCount(adminUserVO))
                .build();
	}
	
	@Override
	public String deleteUser(String id){
		if(mapper.checkUserByUniqId(id)) {
			mapper.deleteUser(id);
			return id;
		}
		return "UserNotFound";
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
