package egovframework.example.idaminuser.controller;

import egovframework.example.cmmn.CustomException;
import egovframework.example.idadminidtt.service.IdAdminIdttManageService;
import egovframework.example.idaminuser.service.IdAdminUserManageService;
import egovframework.example.idaminuser.service.IdAdminUserManageVO;
import org.apache.commons.lang3.StringUtils;
import org.egovframe.rte.fdl.cmmn.exception.FdlException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.HashMap;

@RestController
@RequestMapping("/api/id-admin")
public class IdAdminUserManageController {
    @Resource(name = "idAdminUserManageService")
    private IdAdminUserManageService userManageService;

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody IdAdminUserManageVO idAdminUserManageVO) {

        if(StringUtils.isEmpty(idAdminUserManageVO.getUserId())) {
            throw new CustomException("userId는 필수값입니다.");
        } else if(StringUtils.isEmpty(idAdminUserManageVO.getPassword())) {
            throw new CustomException("password는 필수값입니다.");
        }

        if(!userManageService.isSignUpUser(idAdminUserManageVO)) {
            throw new CustomException("회원정보가 없습니다.");
        }

        return ResponseEntity.ok(userManageService.selectIdAdminUserUniqId(idAdminUserManageVO));
    }

    @GetMapping("/users")
    public ResponseEntity<?> selectIdAdminUserInfoList(@RequestHeader HashMap<String, String> req, @ModelAttribute IdAdminUserManageVO idAdminUserManageVO) {
        IdAdminUserManageVO header = IdAdminUserManageVO.builder().uniqId(req.get("authorization")).build();

        if(!userManageService.isAuthorizedUser(header)) {
            throw new CustomException("회원정보가 없습니다.");
        }

        idAdminUserManageVO.setUniqId(header.getUniqId());

        return ResponseEntity.ok(userManageService.selectIdAdminUserInfoList(idAdminUserManageVO));
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<?> selectIdAdminUserInfoDtl(@RequestHeader HashMap<String, String> req, @PathVariable String userId) {
        IdAdminUserManageVO header = IdAdminUserManageVO.builder().uniqId(req.get("authorization")).build();

        if(!userManageService.isAuthorizedUser(header)) {
            throw new CustomException("회원정보가 없습니다.");
        }

        if(StringUtils.isEmpty(userId)) {
            throw new CustomException("userId는 필수값입니다.");
        }

        return ResponseEntity.ok(userManageService.selectIdAdminUserInfo(IdAdminUserManageVO.builder().uniqId(userId).build()));
    }

    @PutMapping("/users")
    public ResponseEntity<?> insertIdAdminUserInfo(@RequestHeader HashMap<String, String> req, @RequestBody IdAdminUserManageVO idAdminUserManageVO) throws FdlException {
        IdAdminUserManageVO header = IdAdminUserManageVO.builder().uniqId(req.get("authorization")).build();

        if(!userManageService.isAuthorizedUser(header)) {
            throw new CustomException("회원정보가 없습니다.");
        }

        if(StringUtils.isEmpty(idAdminUserManageVO.getUserId())) {
            throw new CustomException("userId는 필수값입니다.");
        } else if(StringUtils.isEmpty(idAdminUserManageVO.getUserNm())) {
            throw new CustomException("userNm은 필수값입니다.");
        } else if(StringUtils.isEmpty(idAdminUserManageVO.getPassword())) {
            throw new CustomException("password는 필수값입니다.");
        } else if(StringUtils.isEmpty(idAdminUserManageVO.getEmailAdres())) {
            throw new CustomException("emailAdres는 필수값입니다.");
        } else if(StringUtils.isEmpty(idAdminUserManageVO.getPhoneNumber())) {
            throw new CustomException("phoneNumber는 필수값입니다.");
        }

        userManageService.insertIdAdminUserInfo(idAdminUserManageVO);

        return ResponseEntity.ok("성공적으로 저장됐습니다.");
    }

    @PutMapping("/users/{userId}")
    public ResponseEntity<?> updateIdAdminUserInfo(@RequestHeader HashMap<String, String> req, @RequestBody IdAdminUserManageVO idAdminUserManageVO, @PathVariable String userId) {
        IdAdminUserManageVO header = IdAdminUserManageVO.builder().uniqId(req.get("authorization")).build();

        if(!userManageService.isAuthorizedUser(header)) {
            throw new CustomException("회원정보가 없습니다.");
        }

        if(StringUtils.isEmpty(userId)) {
            throw new CustomException("userId는 필수값입니다.");
        }

        idAdminUserManageVO.setUserId2(header.getUniqId());
        idAdminUserManageVO.setUniqId(userId);
        userManageService.updateIdAdminUserInfo(idAdminUserManageVO);

        return ResponseEntity.ok("성공적으로 수정됐습니다.");
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<?> deleteIdAdminUserInfo(@RequestHeader HashMap<String, String> req, @PathVariable String userId) {
        IdAdminUserManageVO header = IdAdminUserManageVO.builder().uniqId(req.get("authorization")).build();

        if(!userManageService.isAuthorizedUser(header)) {
            throw new CustomException("회원정보가 없습니다.");
        }

        if(StringUtils.isEmpty(userId)) {
            throw new CustomException("userId는 필수값입니다.");
        }

        userManageService.deleteIdAdminUserInfo(IdAdminUserManageVO.builder().uniqId(userId).build());

        return ResponseEntity.ok("성공적으로 삭제됐습니다.");
    }
}
