package egovframework.example.idadminpoll.service.impl;

import egovframework.example.idadminpoll.dto.IdAdminPollDTO;
import egovframework.example.idadminpoll.dto.IdAdminPollDtlDTO;
import egovframework.example.idadminpoll.service.IdAdminPollManageService;
import egovframework.example.idadminpoll.service.IdAdminPollManageVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@Service("idAdminPollManageService")
public class IdAdminPollManageServiceImpl implements IdAdminPollManageService {

    private static HashMap<String, String> schulGradeCode = new HashMap<String, String>() {{
        put("SCH_01", "유치원");
        put("SCH_02", "초등학교");
        put("SCH_03", "중학교");
        put("SCH_04", "고등학교");
        put("SCH_05", "특수학교");
        put("SCH_99", "예외");
    }};

    private static HashMap<String, String> gradeCode = new HashMap<String, String>() {{
        put("GRADE01", "1학년");
        put("GRADE02", "2학년");
        put("GRADE03", "3학년");
        put("GRADE04", "4학년");
        put("GRADE05", "5학년");
        put("GRADE06", "6학년");
    }};

    private static ArrayList<String> hasImageAnswerPollIdList = new ArrayList<String>() {{
       add("QES00000000000000006");
    }};

    @Resource(name = "idAdminPollManageMapper")
    private IdAdminPollManageMapper mapper;

    @Override
    public List<IdAdminPollDTO> selectIdAdminPollList(IdAdminPollManageVO idAdminPollManageVO) {
        List<IdAdminPollManageVO> list = mapper.selectIdAdminPollList(idAdminPollManageVO);
        List<IdAdminPollDTO> result = new ArrayList<>();

        for(IdAdminPollManageVO vo : list) {

            result.add(
                IdAdminPollDTO.builder()
                    .pollNm(vo.getPollNm())
                    .pollEndde(vo.getPollEndde())
                    .pollBgnde(vo.getPollBgnde())
                    .pageNo(idAdminPollManageVO.getPageNo())
                    .pollTarget(makePollTargetList(vo.getPollTargetList()))
                    .recordCount(idAdminPollManageVO.getRecordCount())
                    .totalCount(mapper.selectIdAdminPollListCount(idAdminPollManageVO))
                    .build()
            );
        }

        return result;
    }

    @Override
    public int selectIdAdminPollListCount(IdAdminPollManageVO idAdminPollManageVO) {
        return mapper.selectIdAdminPollListCount(idAdminPollManageVO);
    }

    @Override
    public List<IdAdminPollDtlDTO> selectIdAdminPollDtl(IdAdminPollManageVO idAdminPollManageVO) {
        List<IdAdminPollManageVO> list = mapper.selectIdAdminPollDtl(idAdminPollManageVO);
        List<IdAdminPollDtlDTO> dto = new ArrayList<>();

        for(IdAdminPollManageVO vo : list) {
            dto.add(IdAdminPollDtlDTO.builder()
                    .schulNm(vo.getSchulNm())
                    .brthy(vo.getBrthdy())
                    .answer(makeAnswerList(idAdminPollManageVO.getPollId(), vo.getQesitmSnList(), vo.getQesitmAnswerList(), vo.getQesitmAnswerImageList()))
                    .emailAdres(vo.getEmailAdres())
                    .sexdstn(vo.getSexdstn())
                    .stGrade(vo.getStGrade())
                    .userNm(vo.getUserNm())
                    .build());
        }

        return dto;
    }

    @Override
    public IdAdminPollManageVO selectIdAdminPollTargetList(String pollId) {
        return mapper.selectIdAdminPollTargetList(pollId);
    }

    @Override
    public void updateIdAdminPollTargetInfo(IdAdminPollManageVO idAdminPollManageVO) {

        mapper.updateIdAdminPollTargetInfo(idAdminPollManageVO);
    }

    @Override
    public HashMap<String, Object> selectSchulGradeInfo() {
            HashMap<String, Object> result = new HashMap<String, Object>() {{
                put("schulGradeCode", schulGradeCode);
                put("gradeCode", gradeCode);
            }};

        return result;
    }

    /**
     * 설문 대상 목록 생성 함수
     * @param list
     * @return
     */
    ArrayList<String> makePollTargetList(String[] list) {
        ArrayList<String> result = new ArrayList<>();

        if(list == null || list.length == 0) {
            return result;
        }

        for(String target : list) {
            String[] arr = target.split("__");
            StringBuilder sb = new StringBuilder();

            if(arr[0].equals("null")) {
                sb.append("null__");
            } else {
                sb.append(mapper.selectSchulName(arr[0]))
                        .append("__");
            }

            sb.append(StringUtils.isEmpty(schulGradeCode.get(arr[1]))
                    ? "null" : schulGradeCode.get(arr[1])
            ).append("__");
            sb.append(StringUtils.isEmpty(gradeCode.get(arr[2]))
                    ? "null" : gradeCode.get(arr[2])
            );

            result.add(sb.toString());
        }

        return result;
    }

    /**
     * 답변 목록 생성 함수
     * @param pollId
     * @param snList
     * @param answerList
     * @param answerImageList
     * @return
     */
    String[] makeAnswerList(String pollId, String[] snList, String[] answerList, String[] answerImageList) {
        String[] result = new String[snList.length];

        for(int i=0; i<snList.length; i++) {
            if(hasImageAnswerPollIdList.contains(pollId)) {
                result[Integer.parseInt(snList[i]) - 1] = answerList[i] + "," + answerImageList[i];
            } else {
                result[Integer.parseInt(snList[i]) - 1] = answerList[i];
            }
        }

        return result;
    }
}
