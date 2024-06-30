package egovframework.example.idaminuser.service.impl;

import egovframework.example.idadminidtt.service.IdAdminIdttManageService;
import egovframework.example.idaminuser.service.IdAdminUserManageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("idAdminUserManageService")
public class IdAdminUserManageServiceImpl implements IdAdminUserManageService {

    @Resource(name = "idAdminUserManageMapper")
    private IdAdminUserManageMapper mapper;
}
