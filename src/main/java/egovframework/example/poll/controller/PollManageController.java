package egovframework.example.poll.controller;

import egovframework.example.cmmn.service.LoginVO;
import egovframework.example.poll.dto.PollDtlDTO;
import egovframework.example.poll.dto.PollNoticeDTO;
import egovframework.example.poll.service.PollManageService;
import egovframework.example.poll.service.PollManageVO;
import egovframework.example.poll.utils.PollManageUtils;
import egovframework.example.user.service.UserManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class PollManageController {
    @Resource(name = "pollManageService")
    private PollManageService pollManageService;

    @Resource(name = "userManageService")
    private UserManageService userManageService;

    @Resource(name = "pollManageUtils")
    private PollManageUtils pollManageUtils;

    /**
     * 회원 마음알기 설문 목록 조회
     */
    @GetMapping("/status")
    public ResponseEntity<?> selectReports(@RequestHeader LoginVO header) {
        PollManageVO pollManageVO = new PollManageVO();

        if(!userManageService.authorizationUser(header)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("유저 인증에 실패했습니다.");
        }

        pollManageVO.setAuthorization(header.getAuthorization());

        if(userManageService.isReallyTeacher(header)) {

            return ResponseEntity.ok(pollManageUtils.makeReportsStatus(pollManageService.selectReportsTeacher(), "teacher"));
        } else {

            return ResponseEntity.ok(pollManageUtils.makeReportsStatus(pollManageService.selectReports(pollManageVO), "student"));
        }
    }

    /**
     * 회원 마음알기 설문 시작
     * @param pollManageVO
     */
    @PutMapping("/start")
    public ResponseEntity<?> insertReportsStatus(@RequestHeader LoginVO header, @RequestBody PollManageVO pollManageVO) {

        if(!userManageService.authorizationUser(header)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("유저 인증에 실패했습니다.");
        }

        pollManageVO.setAuthorization(header.getAuthorization());

        if(pollManageService.selectIsDone(pollManageVO) == 0) {
            pollManageService.insertReportsStatus(pollManageVO);
        }

        return ResponseEntity.ok(pollManageUtils.selectReportsDtl(pollManageVO));
    }

    /**
     * 회원 마음알기 설문 저장
     * @param pollManageVO
     */
    @PutMapping("/save")
    public ResponseEntity<?> insertReports(@RequestHeader LoginVO header, @RequestBody PollManageVO pollManageVO) {

        if(!userManageService.authorizationUser(header)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("유저 인증에 실패했습니다.");
        }

        pollManageVO.setAuthorization(header.getAuthorization());

        if(pollManageService.selectIsDone(pollManageVO) == 1) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else {
            pollManageService.insertReports(pollManageVO);
        }

        return ResponseEntity.ok(pollManageUtils.selectReportsDtl(pollManageVO));
    }

    /**
     * 회원 마음알기 설문 완료
     * @param pollManageVO
     */
    @PutMapping("/complete")
    public ResponseEntity<?> updateReportsStatus(@RequestHeader LoginVO header, @RequestBody PollManageVO pollManageVO) {

        if(!userManageService.authorizationUser(header)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("유저 인증에 실패했습니다.");
        }
//        pollManageVO.setUniqId(header.getUniqId());

        if(pollManageService.selectIsDone(pollManageVO) == 1) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else {
            pollManageService.insertReports(pollManageVO);
            pollManageService.updateReportsStatus(pollManageVO);
        }

        return ResponseEntity.ok().build();
    }

    @GetMapping("/notice")
    public ResponseEntity<?> selectReportsNotice(@RequestHeader LoginVO header,	@RequestParam String pollId){

        if(!userManageService.authorizationUser(header)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("유저 인증에 실패했습니다.");
        }

        PollNoticeDTO pollNoticeDTO = pollManageService.selectReportsNotice(pollId);
        pollNoticeDTO.setAuthorization(header.getAuthorization());

        return ResponseEntity.ok(pollNoticeDTO);
    }
}
