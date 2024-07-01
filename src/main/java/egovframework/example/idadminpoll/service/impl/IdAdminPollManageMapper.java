package egovframework.example.idadminpoll.service.impl;

import egovframework.example.idadminpoll.dto.IdAdminPollDTO;
import egovframework.example.idadminpoll.service.IdAdminPollManageVO;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import java.util.List;

@Mapper("idAdminPollManageMapper")
public interface IdAdminPollManageMapper {
    /**
     * ID ADMIN 설문 목록 조회
     * @param idAdminPollManageVO
     * @return
     */
    List<IdAdminPollDTO> selectIdAdminPollList(IdAdminPollManageVO idAdminPollManageVO);

    /**
     * ID ADMIN 설문 대상 변경
     * @param idAdminPollManageVO
     */
    void updateIdAdminPollTargetInfo(IdAdminPollManageVO idAdminPollManageVO);
}
