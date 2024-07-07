package egovframework.example.idadminidtt.service.impl;

import egovframework.example.idadminidtt.dto.IdAdminIdttDTO;
import egovframework.example.idadminidtt.dto.IdAdminIdttResultDTO;
import egovframework.example.idadminidtt.service.IdAdminIdttManageService;
import egovframework.example.idadminidtt.service.IdAdminIdttManageVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;

@Service("idAdminIdttManageService")
public class IdAdminIdttManageServiceImpl implements IdAdminIdttManageService {

    @Resource(name = "idAdminIdttManageMapper")
    private IdAdminIdttManageMapper mapper;

    @Override
    public IdAdminIdttResultDTO<IdAdminIdttDTO> selectReportsList(IdAdminIdttManageVO idAdminIdttManageVO) {
        ArrayList<IdAdminIdttManageVO> listVO = mapper.selectReportsList(idAdminIdttManageVO);
        ArrayList<IdAdminIdttDTO> listDto = new ArrayList<>();

        for(IdAdminIdttManageVO vo : listVO) {
            ArrayList<String> qesAnswerMap = new ArrayList<>();

            if(vo.getQesAnswerList() != null) {
                for(int i=0; i<vo.getQesAnswerList().length; i++) {
                    qesAnswerMap.add(vo.getQesAnswerList()[i]);
                }
            }

            listDto.add(IdAdminIdttDTO.builder()
                    .schulNm(vo.getSchulNm())
                    .qesAnswer(qesAnswerMap)
                    .userNm(vo.getUserNm())
                    .userSpaceOrgInfo(vo.getUserSpaceOrgInfo())
                    .build());
        }

        return IdAdminIdttResultDTO.<IdAdminIdttDTO>builder()
                .idttList(listDto)
                .pageNo(idAdminIdttManageVO.getPageNo())
                .recordCount(idAdminIdttManageVO.getLimit())
                .totalCount(mapper.selectReportsListCount(idAdminIdttManageVO))
                .build();
    }
}
