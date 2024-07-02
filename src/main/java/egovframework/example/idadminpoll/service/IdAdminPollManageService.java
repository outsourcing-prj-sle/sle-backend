package egovframework.example.idadminpoll.service;

import egovframework.example.idadminpoll.dto.IdAdminPollDTO;
import egovframework.example.idadminpoll.dto.IdAdminPollDtlDTO;

import java.util.HashMap;
import java.util.List;

public interface IdAdminPollManageService {
    /**
     * ID ADMIN 설문 목록 조회
     * @param idAdminPollManageVO
     * @return
     */
    List<IdAdminPollDTO> selectIdAdminPollList(IdAdminPollManageVO idAdminPollManageVO);

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
    List<IdAdminPollDtlDTO> selectIdAdminPollDtl(IdAdminPollManageVO idAdminPollManageVO);

    /**
     * ID ADMIN 설문 대상 목록 조회
     * @param pollId
     */
    IdAdminPollManageVO selectIdAdminPollTargetList(String pollId);

    /**
     * ID ADMIN 설문 대상 등록
     * @param idAdminPollManageVO
     */
    void updateIdAdminPollTargetInfo(IdAdminPollManageVO idAdminPollManageVO);

    /**
     * 학교급, 학년 정보 조회
     */
    HashMap<String, Object> selectSchulGradeInfo();
}
