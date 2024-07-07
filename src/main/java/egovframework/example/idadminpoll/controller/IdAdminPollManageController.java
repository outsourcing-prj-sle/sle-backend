package egovframework.example.idadminpoll.controller;

import egovframework.example.cmmn.CustomException;
import egovframework.example.cmmn.service.SchulCodeChache;
import egovframework.example.idadminpoll.service.IdAdminPollManageService;
import egovframework.example.idadminpoll.service.IdAdminPollManageVO;
import egovframework.example.idaminuser.service.IdAdminUserManageService;
import egovframework.example.idaminuser.service.IdAdminUserManageVO;
import egovframework.example.naver.dto.GneInfoDto;
import egovframework.example.naver.dto.GneSchulDTO;
import egovframework.example.naver.service.impl.NaverServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/id-admin")
public class IdAdminPollManageController {
    @Resource(name = "idAdminPollManageService")
    private IdAdminPollManageService pollManageService;

    @Resource(name = "idAdminUserManageService")
    private IdAdminUserManageService userManageService;

    @Resource(name = "naverServiceImpl")
    private NaverServiceImpl naverService;

    @Resource(name = "schulCodeChache")
    private SchulCodeChache schulCodeChache;

    @GetMapping("/reports")
    public ResponseEntity<?> selectIdAdminPollList(@RequestHeader HashMap<String, String> req, @ModelAttribute IdAdminPollManageVO idAdminPollManageVO) {
        IdAdminUserManageVO header = IdAdminUserManageVO.builder().uniqId(req.get("authorization")).build();

        if(!userManageService.isAuthorizedUser(header)) {
            throw new CustomException("회원정보가 없습니다.");
        }

        return ResponseEntity.ok(pollManageService.selectIdAdminPollList(idAdminPollManageVO));
    }

    @GetMapping("/reports/{pollId}")
    public ResponseEntity<?> selectIdAdminPollInfo(@RequestHeader HashMap<String, String> req, @PathVariable String pollId, @ModelAttribute IdAdminPollManageVO idAdminPollManageVO) {
        IdAdminUserManageVO header = IdAdminUserManageVO.builder().uniqId(req.get("authorization")).build();

        if(!userManageService.isAuthorizedUser(header)) {
            throw new CustomException("회원정보가 없습니다.");
        }

        idAdminPollManageVO.setPollId(pollId);
        return ResponseEntity.ok(pollManageService.selectIdAdminPollDtl(idAdminPollManageVO));
    }

    @PutMapping("/reports/target/{pollId}")
    public ResponseEntity<?> insertIdAdminPollInfo(@RequestHeader HashMap<String, String> req, @PathVariable String pollId, @RequestBody IdAdminPollManageVO idAdminPollManageVO) {
        IdAdminUserManageVO header = IdAdminUserManageVO.builder().uniqId(req.get("authorization")).build();

        if(!userManageService.isAuthorizedUser(header)) {
            throw new CustomException("회원정보가 없습니다.");
        }

        if(StringUtils.isEmpty(idAdminPollManageVO.getPollTarget())) {
            throw new CustomException("pollTarget은 필수값입니다.");
        } else if(StringUtils.isEmpty(idAdminPollManageVO.getPollBgnde())) {
            throw new CustomException("pollBgnde는 필수값입니다.");
        } else if(StringUtils.isEmpty(idAdminPollManageVO.getPollEndde())) {
            throw new CustomException("pollEndde는 필수값입니다.");
        }

        idAdminPollManageVO.setUniqId(req.get("authorization"));
        idAdminPollManageVO.setPollId(pollId);
        pollManageService.updateIdAdminPollTargetInfo(idAdminPollManageVO);

        return ResponseEntity.ok("성공적으로 수정됐습니다.");
    }

    @GetMapping("/schulGradeCode")
    public ResponseEntity<?> selectSchulGradeInfo(@RequestHeader HashMap<String, String> req) {
        IdAdminUserManageVO header = IdAdminUserManageVO.builder().uniqId(req.get("authorization")).build();

        if(!userManageService.isAuthorizedUser(header)) {
            throw new CustomException("회원정보가 없습니다.");
        }

        return ResponseEntity.ok(pollManageService.selectSchulGradeInfo());
    }

    @GetMapping("/schulCode")
    public ResponseEntity<?> selectSchulInfo(@RequestHeader HashMap<String, String> req) {
        IdAdminUserManageVO header = IdAdminUserManageVO.builder().uniqId(req.get("authorization")).build();

        if(!userManageService.isAuthorizedUser(header)) {
            throw new CustomException("회원정보가 없습니다.");
        }

        return ResponseEntity.ok(schulCodeChache.getSchulMap());
    }
}
