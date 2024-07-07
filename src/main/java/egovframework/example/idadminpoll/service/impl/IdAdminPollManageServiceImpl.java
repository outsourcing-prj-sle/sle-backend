package egovframework.example.idadminpoll.service.impl;

import egovframework.example.cmmn.service.Constant;
import egovframework.example.cmmn.service.SchulCodeChache;
import egovframework.example.idadminpoll.dto.IdAdminPollDTO;
import egovframework.example.idadminpoll.dto.IdAdminPollDtlDTO;
import egovframework.example.idadminpoll.dto.IdAdminPollDtlResultDTO;
import egovframework.example.idadminpoll.dto.IdAdminPollResultDTO;
import egovframework.example.idadminpoll.service.IdAdminPollManageService;
import egovframework.example.idadminpoll.service.IdAdminPollManageVO;
import egovframework.example.naver.dto.GneInfoDto;
import egovframework.example.naver.dto.GneSchulDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service("idAdminPollManageService")
public class IdAdminPollManageServiceImpl implements IdAdminPollManageService {
    @Resource(name = "idAdminPollManageMapper")
    private IdAdminPollManageMapper mapper;

    @Autowired
    private SchulCodeChache schulCodeChache;

    private static ArrayList<String> hasImageAnswerPollIdList = new ArrayList<String>() {{
       add("QES00000000000000006");
    }};

    @Override
    public IdAdminPollResultDTO<IdAdminPollDTO> selectIdAdminPollList(IdAdminPollManageVO idAdminPollManageVO) {
        List<IdAdminPollManageVO> list = mapper.selectIdAdminPollList(idAdminPollManageVO);
        List<IdAdminPollDTO> result = new ArrayList<>();

        for(IdAdminPollManageVO vo : list) {

            result.add(
                IdAdminPollDTO.builder()
                    .pollId(vo.getPollId())
                    .pollNm(vo.getPollNm())
                    .pollEndde(vo.getPollEndde())
                    .pollBgnde(vo.getPollBgnde())
                    .pollTarget(makePollTargetList(vo.getPollTargetList()))
                    .pollTargetCode(vo.getPollTargetList())
                    .build()
            );
        }

        return IdAdminPollResultDTO.<IdAdminPollDTO>builder()
                .pollList(result)
                .pageNo(idAdminPollManageVO.getPageNo())
                .limit(idAdminPollManageVO.getLimit())
                .totalCount(mapper.selectIdAdminPollListCount(idAdminPollManageVO))
                .build();
    }

    @Override
    public int selectIdAdminPollListCount(IdAdminPollManageVO idAdminPollManageVO) {
        return mapper.selectIdAdminPollListCount(idAdminPollManageVO);
    }

    @Override
    public IdAdminPollDtlResultDTO<IdAdminPollDtlDTO> selectIdAdminPollDtl(IdAdminPollManageVO idAdminPollManageVO) {
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

        return IdAdminPollDtlResultDTO.<IdAdminPollDtlDTO>builder()
                .pageNo(idAdminPollManageVO.getPageNo())
                .limit(idAdminPollManageVO.getLimit())
                .totalCount(mapper.selectIdAdminPollDtlCount(idAdminPollManageVO))
                .pollDtlList(dto)
                .build();
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
                put("schulGradeCode", Constant.schulGradeCode);
                put("gradeCode", Constant.gradeCode);
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
                GneInfoDto<List<GneSchulDTO>> gneInfoDto = schulCodeChache.getSchulMap();

                for(GneSchulDTO dto : gneInfoDto.getData()) {
                    if(dto.getSchulCode().equals(arr[0])) {
                        sb.append(dto.getSchulNm()).append("__");
                        break;
                    }
                }
            }

            sb.append(StringUtils.isEmpty(Constant.schulGradeCode.get(arr[1]))
                    ? "null" : Constant.schulGradeCode.get(arr[1])
            ).append("__");
            sb.append(StringUtils.isEmpty(Constant.gradeCode.get(arr[2]))
                    ? "null" : Constant.gradeCode.get(arr[2])
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
