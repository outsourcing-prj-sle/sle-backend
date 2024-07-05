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
    List<IdAdminPollManageVO> selectIdAdminPollList(IdAdminPollManageVO idAdminPollManageVO);

    /**
     * ID ADMIN 설문 총 수
     * @param idAdminPollManageVO
     * @return
     */
    int selectIdAdminPollListCount(IdAdminPollManageVO idAdminPollManageVO);

    /**
     * ID ADMIN 설문 상세 조회
     * @param idAdminPollManageVO
     * @return
     */
    List<IdAdminPollManageVO> selectIdAdminPollDtl(IdAdminPollManageVO idAdminPollManageVO);

    /**
     * ID ADMIN 설문 상세 총 수
     * @param idAdminPollManageVO
     * @return
     */
    int selectIdAdminPollDtlCount(IdAdminPollManageVO idAdminPollManageVO);

    /**
     * ID ADMIN 설문 대상 목록 조회
     * @param pollId
     */
    IdAdminPollManageVO selectIdAdminPollTargetList(String pollId);

    /**
     * ID ADMIN 설문 대상 변경
     * @param idAdminPollManageVO
     */
    void updateIdAdminPollTargetInfo(IdAdminPollManageVO idAdminPollManageVO);

    /**
     * ID ADMIN 학교명 조회
     * @param schulCode
     * @return
     */
    String selectSchulName(String schulCode);
}
