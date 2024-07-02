package egovframework.example.idadminidtt.service.impl;

import egovframework.example.idadminidtt.dto.IdAdminIdttResultDTO;
import egovframework.example.idadminidtt.service.IdAdminIdttManageService;
import egovframework.example.idadminidtt.service.IdAdminIdttManageVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;

@Service("idAdminIdttManageService")
public class IdAdminIdttManageServiceImpl implements IdAdminIdttManageService {

    @Resource(name = "idAdminIdttManageMapper")
    private IdAdminIdttManageMapper mapper;

    @Override
    public ArrayList<IdAdminIdttResultDTO> selectReportsList(IdAdminIdttManageVO idAdminIdttManageVO) {
        ArrayList<IdAdminIdttManageVO> listVO = mapper.selectReportsList(idAdminIdttManageVO);
        ArrayList<IdAdminIdttResultDTO> result = new ArrayList<>();

        for(IdAdminIdttManageVO vo : listVO) {
            ArrayList<String> qesAnswerMap = new ArrayList<>();

            if(vo.getQesAnswerList() != null) {
                for(int i=0; i<vo.getQesAnswerList().length; i++) {
                    qesAnswerMap.add(vo.getQesAnswerList()[i]);
                }
            }

            result.add(IdAdminIdttResultDTO.builder()
                    .schulNm(vo.getSchulNm())
                    .qesAnswer(qesAnswerMap)
                    .userNm(vo.getUserNm())
                    .userSpaceOrgInfo(vo.getUserSpaceOrgInfo())
                    .build());
        }

        return result;
    }
}
