package egovframework.example.idadminidtt.service;

import egovframework.example.idadminidtt.dto.IdAdminIdttResultDTO;

import java.util.ArrayList;

public interface IdAdminIdttManageService {
    /**
     * idtt 관리 선생님 평가 목록 조회
     * @param idAdminIdttManageVO
     * @return
     */
    ArrayList<IdAdminIdttResultDTO> selectReportsList(IdAdminIdttManageVO idAdminIdttManageVO);
}
