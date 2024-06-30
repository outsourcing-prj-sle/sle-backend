package egovframework.example.idaminuser.controller;

import egovframework.example.idadminidtt.service.IdAdminIdttManageService;
import egovframework.example.idaminuser.service.IdAdminUserManageService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/IDAdmin")
public class IdAdminUserManageController {
    @Resource(name = "idAdminUserManageService")
    private IdAdminUserManageService userManageService;


}
