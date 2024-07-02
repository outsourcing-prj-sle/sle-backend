package egovframework.example.poll.controller;

import egovframework.example.cmmn.CustomException;
import egovframework.example.cmmn.service.LoginVO;
import egovframework.example.poll.dto.PollNoticeDTO;
import egovframework.example.poll.service.PollManageService;
import egovframework.example.poll.service.PollManageVO;
import egovframework.example.user.service.UserManageService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;

@RestController
@RequestMapping("/api/reports")
public class PollManageController {
    @Resource(name = "pollManageService")
    private PollManageService pollManageService;

    @Resource(name = "userManageService")
    private UserManageService userManageService;

    /**
     * 회원 마음알기 설문 목록 조회
     */
    @GetMapping("/status")
    public ResponseEntity<?> selectReports(@RequestHeader HashMap<String, String> req) {
        LoginVO header = LoginVO.builder().authorization(req.get("authorization")).build();

        PollManageVO pollManageVO = new PollManageVO();

        if(!userManageService.authorizationUser(header)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("유저 인증에 실패했습니다.");
        }

        pollManageVO.setAuthorization(header.getAuthorization());

        if(userManageService.isReallyTeacher(header)) {

            return ResponseEntity.ok(pollManageService.makeReportsStatus(pollManageService.selectReportsTeacher(), "teacher"));
        } else {

            return ResponseEntity.ok(pollManageService.makeReportsStatus(pollManageService.selectReports(pollManageVO), "student"));
        }
    }

    /**
     * 회원 마음알기 설문 시작
     * @param pollManageVO
     */
    @PutMapping("/start")
    public ResponseEntity<?> insertReportsStatus(@RequestHeader HashMap<String, String> req, @RequestBody PollManageVO pollManageVO) {
        LoginVO header = LoginVO.builder().authorization(req.get("authorization")).build();

        if(!userManageService.authorizationUser(header)) {
            throw new CustomException("유저 인증에 실패했습니다.");
        } else if(StringUtils.isEmpty(pollManageVO.getPollId())) {
            throw new CustomException("pollId는 필수값입니다.");
        }

        pollManageVO.setAuthorization(header.getAuthorization());

        if(pollManageService.selectIsDone(pollManageVO) == 0) {
            pollManageService.insertReportsStatus(pollManageVO);
        }

        return ResponseEntity.ok(pollManageService.selectReportsDtlItm(pollManageVO));
    }

    /**
     * 회원 마음알기 설문 저장
     * @param pollManageVO
     */
    @PutMapping("/save")
    public ResponseEntity<?> insertReports(@RequestHeader HashMap<String, String> req, @RequestBody PollManageVO pollManageVO) {
        LoginVO header = LoginVO.builder().authorization(req.get("authorization")).build();

        if(!userManageService.authorizationUser(header)) {
            throw new CustomException("유저 인증에 실패했습니다.");
        } else if(StringUtils.isEmpty(pollManageVO.getPollId())) {
            throw new CustomException("pollId는 필수값입니다.");
        } else if(StringUtils.isEmpty(pollManageVO.getQesitmAnswer())) {
            throw new CustomException("qesitmAnswer는 필수값입니다.");
        } else if(StringUtils.isEmpty(pollManageVO.getQesitmAnswerImage())) {
            throw new CustomException("qesitmAnswerImage는 필수값입니다.");
        }

        pollManageVO.setAuthorization(header.getAuthorization());

        if(pollManageService.selectIsDone(pollManageVO) == 1) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else {
            pollManageService.insertReports(pollManageVO);
        }

        return ResponseEntity.ok(pollManageService.selectReportsDtlItm(pollManageVO));
    }

    /**
     * 회원 마음알기 설문 완료
     * @param pollManageVO
     */
    @PutMapping("/complete")
    public ResponseEntity<?> updateReportsStatus(@RequestHeader HashMap<String, String> req, @RequestBody PollManageVO pollManageVO) {
        LoginVO header = LoginVO.builder().authorization(req.get("authorization")).build();

        if(!userManageService.authorizationUser(header)) {
            throw new CustomException("유저 인증에 실패했습니다.");
        } else if(StringUtils.isEmpty(pollManageVO.getPollId())) {
            throw new CustomException("pollId는 필수값입니다.");
        }

        pollManageVO.setAuthorization(header.getAuthorization());

        if(pollManageService.selectIsDone(pollManageVO) == 1) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else {
            pollManageService.updateReportsStatus(pollManageVO);
        }

        return ResponseEntity.ok().build();
    }

    @GetMapping("/notice")
    public ResponseEntity<?> selectReportsNotice(@RequestHeader HashMap<String, String> req, @RequestParam String pollId){
        LoginVO header = LoginVO.builder().authorization(req.get("authorization")).build();

        if(!userManageService.authorizationUser(header)) {
            throw new CustomException("유저 인증에 실패했습니다.");
        } else if(StringUtils.isEmpty(pollId)) {
            throw new CustomException("pollId는 필수값입니다.");
        }

        PollNoticeDTO pollNoticeDTO = pollManageService.selectReportsNotice(pollId);
        pollNoticeDTO.setAuthorization(header.getAuthorization());

        return ResponseEntity.ok(pollNoticeDTO);
    }
}
