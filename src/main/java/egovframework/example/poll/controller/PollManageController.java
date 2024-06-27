package egovframework.example.poll.controller;

import egovframework.example.cmmn.service.LoginVO;
import egovframework.example.poll.dto.PollDTO;
import egovframework.example.poll.dto.PollDtlDTO;
import egovframework.example.poll.dto.PollNoticeDTO;
import egovframework.example.poll.service.PollManageService;
import egovframework.example.poll.service.PollManageVO;
import egovframework.example.user.service.UserManageService;
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

    /**
     * 회원 마음알기 설문 목록 조회
     */
    @GetMapping("/status")
    public ResponseEntity<?> selectReports(@RequestHeader LoginVO header) {
        PollManageVO pollManageVO = new PollManageVO();

        if(!pollManageService.authorizationUser(header)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("유저 인증에 실패했습니다.");
        }

//        pollManageVO.setUniqId(header.getUniqId());
        HashMap<String, List<PollDTO>> map = new HashMap<>();

        if(userManageService.isReallyTeacher(header)) {
            List<PollManageVO> list = pollManageService.selectReportsTeacher();

            List<PollDTO> progress = new ArrayList<>();
            List<PollDTO> expired = new ArrayList<>();

            for(PollManageVO vo : list) {
                PollDTO poll = new PollDTO(vo);

                if(poll.getExpired()) {
                    expired.add(poll);
                } else {
                    progress.add(poll);
                }
            }

            map.put("todo", progress);
            map.put("done", expired);

        } else {
            List<PollManageVO> list = pollManageService.selectReports(pollManageVO);

            List<PollDTO> todos = new ArrayList<>();
            List<PollDTO> dones = new ArrayList<>();

            for(PollManageVO vo : list) {
                PollDTO poll = new PollDTO(vo);

                if(poll.getStatus().equals("done")) {
                    dones.add(poll);
                } else {
                    todos.add(poll);
                }
            }

            map.put("todo", todos);
            map.put("done", dones);
        }

        return ResponseEntity.ok(map);
    }

    /**
     * 회원 마음알기 설문 상세 함수
     * @param pollManageVO
     * @return
     */
    public PollDtlDTO selectReportsDtl(PollManageVO pollManageVO) {
        if(StringUtils.isEmpty(pollManageVO.getUniqId()) || StringUtils.isEmpty(pollManageVO.getPollId())) {
            return PollDtlDTO.builder().build();
        }

        PollManageVO vo = pollManageService.selectReportsDtl(pollManageVO);
        List<PollManageVO> qnaList = pollManageService.selectReportsQnA(pollManageVO);

        StringBuilder stepList = new StringBuilder();

        ArrayList<HashMap<String, Object>> metaList = new ArrayList<HashMap<String,Object>>();

        String[] randSnList = vo.getQesitmSnList().split(",");

        if(vo.getQesitmSn() == null) {
            for(int i=0; i<randSnList.length; i++) {
                int sn = Integer.parseInt(randSnList[i]);

                stepList.append(sn).append(":/");

                if(i < randSnList.length-1) {
                    stepList.append(",");
                }
            }
        } else {
            String[] snList = vo.getQesitmSn().split(",");

            for(int i=0; i<randSnList.length; i++) {
                int sn = Integer.parseInt(randSnList[i]);

                for(int j=0; j<snList.length; j++) {

                    String[] q = snList[j].split(":");

                    if(sn == Integer.parseInt(q[0])) {
                        stepList.append(snList[j]).append(",");
                        break;
                    }

                    if(j == snList.length-1) {
                        stepList.append(sn).append(":/,");
                    }
                }

                if(i == randSnList.length-1) {
                    stepList.deleteCharAt(stepList.length()-1);
                }
            }
        }

        for(PollManageVO qna : qnaList) {
            HashMap<String, Object> metaMap = new HashMap<String, Object>();
            ArrayList<String> answerText = new ArrayList<String>();
            ArrayList<String> answerImage = new ArrayList<String>();

            metaMap.put("Q", qna.getQesitmSj());

            for(String at : qna.getQesitmAnswerText().split(",")) {
                answerText.add(at);
            }
            metaMap.put("AT", answerText);

            for(String ai : qna.getQesitmAnswerImage().split(",")) {
                answerImage.add(ai);
            }
            metaMap.put("AI", answerImage);

            metaList.add(metaMap);
        }

        PollDtlDTO pollDtl = PollDtlDTO.builder()
                .status(vo.getStatus())
                .pollNm(vo.getPollNm())
                .startDate(vo.getStartDate())
                .endDate(vo.getEndDate())
                .step(stepList.toString())
                .metadata(metaList)
                .isSave(vo.getIsSave())
                .isVoice(vo.getIsVoice())
                .build();

        return pollDtl;
    }


    /**
     * 회원 마음알기 설문 저장
     * @param pollId
     * @param pollManageVO
     */
    @PutMapping("/save")
    public ResponseEntity<?> insertReports(@RequestHeader LoginVO header, @RequestBody PollManageVO pollManageVO) {

        if(!pollManageService.authorizationUser(header)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("유저 인증에 실패했습니다.");
        }

//        pollManageVO.setUniqId(header.getUniqId());

        if(pollManageService.selectIsDone(pollManageVO) == 1) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else {
            pollManageService.insertReports(pollManageVO);
        }

        return ResponseEntity.ok(selectReportsDtl(pollManageVO));
    }


    /**
     * 회원 마음알기 설문 시작
     * @param pollManageVO
     */
    @PutMapping("/start")
    public ResponseEntity<?> insertReportsStatus(@RequestHeader LoginVO header, @RequestBody PollManageVO pollManageVO) {

        if(!pollManageService.authorizationUser(header)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("유저 인증에 실패했습니다.");
        }
//        pollManageVO.setUniqId(header.getUniqId());

        if(pollManageService.selectIsDone(pollManageVO) == 1) {
            return ResponseEntity.ok(selectReportsDtl(pollManageVO));
        } else {
            pollManageService.insertReportsStatus(pollManageVO);
        }

        return ResponseEntity.ok(selectReportsDtl(pollManageVO));
    }

    /**
     * 회원 마음알기 설문 완료
     * @param pollManageVO
     */
    @PutMapping("/complete")
    public ResponseEntity<?> updateReportsStatus(@RequestHeader LoginVO header, @RequestBody PollManageVO pollManageVO) {

        if(!pollManageService.authorizationUser(header)) {
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

        if(!pollManageService.authorizationUser(header)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("유저 인증에 실패했습니다.");
        }

        PollNoticeDTO pollNoticeDTO = pollManageService.selectReportsNotice(pollId);
//        pollNoticeDTO.setUniqId(header.getUniqId());

        return ResponseEntity.ok(pollNoticeDTO);
    }
}
