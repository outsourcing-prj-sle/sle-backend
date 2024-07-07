package egovframework.example.idadminidtt.service;

import egovframework.example.idadminidtt.dto.IdAdminIdttDTO;
import egovframework.example.idadminidtt.dto.IdAdminIdttResultDTO;

import java.util.HashMap;
import java.util.List;

public interface IdAdminIdttManageService {
    /**
     * idtt 관리 선생님 평가 목록 조회
     * @param idAdminIdttManageVO
     * @return
     */
    IdAdminIdttResultDTO<IdAdminIdttDTO> selectReportsList(IdAdminIdttManageVO idAdminIdttManageVO);

    /**
     * DB 내 학교 목록 조회
     * @return
     */
    List<HashMap<String, String>> selectSchulList();
}
