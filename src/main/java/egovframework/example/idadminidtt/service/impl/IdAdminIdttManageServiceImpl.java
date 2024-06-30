package egovframework.example.idadminidtt.service.impl;

import egovframework.example.idadminidtt.service.IdAdminIdttManageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("idAdminIdttManageService")
public class IdAdminIdttManageServiceImpl implements IdAdminIdttManageService {

    @Resource(name = "idAdminIdttManageMapper")
    private IdAdminIdttManageMapper mapper;
}
