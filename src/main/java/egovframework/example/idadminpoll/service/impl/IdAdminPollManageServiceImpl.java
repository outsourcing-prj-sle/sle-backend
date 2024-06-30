package egovframework.example.idadminpoll.service.impl;

import egovframework.example.idadminidtt.service.IdAdminIdttManageService;
import egovframework.example.idadminidtt.service.impl.IdAdminIdttManageMapper;
import egovframework.example.idadminpoll.service.IdAdminPollManageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("idAdminPollManageService")
public class IdAdminPollManageServiceImpl implements IdAdminPollManageService {

    @Resource(name = "idAdminPollManageMapper")
    private IdAdminPollManageMapper mapper;
}
