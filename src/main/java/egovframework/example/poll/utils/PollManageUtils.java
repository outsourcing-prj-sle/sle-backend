package egovframework.example.poll.utils;

import egovframework.example.poll.dto.PollDTO;
import egovframework.example.poll.dto.PollDtlDTO;
import egovframework.example.poll.dto.PollMetaDTO;
import egovframework.example.poll.service.PollManageService;
import egovframework.example.poll.service.PollManageVO;
import egovframework.example.user.service.UserManageService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@Component
public class PollManageUtils {
    @Resource(name = "pollManageService")
    private PollManageService pollManageService;

    @Resource(name = "userManageService")
    private UserManageService userManageService;

    /**
     * 보고서 목록 상태 생성
     * @param list
     * @param se
     * @return
     */
    public HashMap<String, List<PollDTO>> makeReportsStatus(List<PollDTO> list, String se) {
        List<PollDTO> todo = new ArrayList<>();
        List<PollDTO> done = new ArrayList<>();

        if(StringUtils.isNotEmpty(se) && se.equals("teacher")) {
            for(PollDTO dto : list) {
                if(dto.getExpired()) {
                    todo.add(dto);
                } else {
                    done.add(dto);
                }
            }
        }

        if(StringUtils.isNotEmpty(se) && se.equals("student")) {
            for(PollDTO dto : list) {
                if(dto.getStatus().equals("done")) {
                    done.add(dto);
                } else {
                    todo.add(dto);
                }
            }
        }

        return new HashMap<String, List<PollDTO>>(){{
            put("todo", todo);
            put("done", done);
        }};
    }

    /**
     * 랜덤 문제 문자열 생성
     * @param size
     * @return
     */
    public String makeRandomString(int size) {

        List<Integer> numbers = new ArrayList<>();

        for(int i=1; i<=size; i++) {
            numbers.add(i);
        }

        Collections.shuffle(numbers);

        StringBuilder sb = new StringBuilder();

        for(int i=0; i < size; i++) {
            sb.append(numbers.get(i));
            if(i < size-1) {
                sb.append(",");
            }
        }

        return sb.toString();
    }

    /**
     * 회원 마음알기 설문 상세 함수
     * @param pollManageVO
     * @return
     */
    public PollDtlDTO selectReportsDtl(PollManageVO pollManageVO) {
        if(StringUtils.isEmpty(pollManageVO.getAuthorization()) || StringUtils.isEmpty(pollManageVO.getPollId())) {
            return PollDtlDTO.builder().build();
        }

        PollManageVO vo = pollManageService.selectReportsDtl(pollManageVO);

        StringBuilder stepList = new StringBuilder();

        for(int i=0; i<vo.getQesitmSnList().length; i++) {
            stepList.append(vo.getQesitmSnList()[i])
                    .append(":");

            if(i < vo.getQesitmAnswerList().length) {
                stepList.append(vo.getQesitmAnswerList()[i])
                        .append("/");
            } else {
                stepList.append("0/");
            }

            if(i < vo.getQesitmAnswerImageList().length) {
                stepList.append(vo.getQesitmAnswerImageList()[i])
                        .append(",");
            } else {
                stepList.append("0,");
            }

            if(i == vo.getQesitmSnList().length - 1) {
                stepList.deleteCharAt(stepList.length() - 1);
            }
        }

        return PollDtlDTO.builder()
                .status(vo.getStatus())
                .pollNm(vo.getPollNm())
                .startDate(vo.getStartDate())
                .endDate(vo.getEndDate())
                .step(stepList.toString())
                .metadata(makeMetaList(pollManageVO))
                .isSave(vo.getIsSave())
                .isVoice(vo.getIsVoice())
                .build();
    }

    /**
     * 마음알기 설문 Q(질문), AT(답변문자), AI(답변이미지) 메타데이터 생성
     * @param pollManageVO
     * @return
     */
    public ArrayList<PollMetaDTO> makeMetaList(PollManageVO pollManageVO) {

        List<PollManageVO> qnaList = pollManageService.selectReportsQnA(pollManageVO);

        ArrayList<PollMetaDTO> metaList = new ArrayList<PollMetaDTO>();

        for(PollManageVO qna : qnaList) {
            ArrayList<String> answerText = new ArrayList<String>();
            ArrayList<String> answerImage = new ArrayList<String>();

            for(String at : qna.getQesitmAnswerText().split(",")) {
                answerText.add(at);
            }

            for(String ai : qna.getQesitmAnswerImage().split(",")) {
                answerImage.add(ai);
            }

            metaList.add(
                PollMetaDTO.builder()
                    .Q(qna.getQesitmSj())
                    .AT(answerText)
                    .AI(answerImage)
                    .build()
            );
        }

        return metaList;
    }
}