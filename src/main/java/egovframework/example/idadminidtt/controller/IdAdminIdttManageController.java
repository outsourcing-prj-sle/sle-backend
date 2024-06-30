package egovframework.example.idadminidtt.controller;

import egovframework.example.idadminidtt.service.IdAdminIdttManageService;
import egovframework.example.idadminpoll.service.IdAdminPollManageService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/IDAdmin")
public class IdAdminIdttManageController {
    @Resource(name = "idAdminPollManageService")
    private IdAdminPollManageService pollManageService;

}
