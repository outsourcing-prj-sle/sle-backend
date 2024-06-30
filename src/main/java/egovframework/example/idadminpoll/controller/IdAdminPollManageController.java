package egovframework.example.idadminpoll.controller;

import egovframework.example.idadminidtt.service.IdAdminIdttManageService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/IDAdmin")
public class IdAdminPollManageController {
    @Resource(name = "idAdminIdttManageService")
    private IdAdminIdttManageService idttManageService;


}
