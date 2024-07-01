package egovframework.example.idadminidtt.controller;

import egovframework.example.cmmn.CustomException;
import egovframework.example.idadminidtt.service.IdAdminIdttManageService;
import egovframework.example.idadminidtt.service.IdAdminIdttManageVO;
import egovframework.example.idadminpoll.service.IdAdminPollManageVO;
import egovframework.example.idaminuser.service.IdAdminUserManageService;
import egovframework.example.idaminuser.service.IdAdminUserManageVO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;

@RestController
@RequestMapping("/api/id-admin")
public class IdAdminIdttManageController {
    @Resource(name = "idAdminIdttManageService")
    private IdAdminIdttManageService idttManageService;

    @Resource(name = "idAdminUserManageService")
    private IdAdminUserManageService userManageService;

    @GetMapping("/reports")
    public ResponseEntity selectReportsList(@RequestHeader HashMap<String, String> req, @RequestBody IdAdminIdttManageVO vo) {
        IdAdminUserManageVO header = IdAdminUserManageVO.builder().userId(req.get("authorization")).build();

        if(!userManageService.isAuthorizedUser(header)) {
            throw new CustomException("회원정보가 없습니다.");
        }

        return ResponseEntity.ok(idttManageService.selectReportsList(vo));
    }
}
