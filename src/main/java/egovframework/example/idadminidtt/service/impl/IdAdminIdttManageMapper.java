package egovframework.example.idadminidtt.service.impl;

import egovframework.example.idadminidtt.service.IdAdminIdttManageVO;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Mapper("idAdminIdttManageMapper")
public interface IdAdminIdttManageMapper {
    /**
     * idtt 관리 선생님 평가 목록 조회
     * @param idAdminIdttManageVO
     * @return
     */
    ArrayList<IdAdminIdttManageVO> selectReportsList(IdAdminIdttManageVO idAdminIdttManageVO);

    /**
     * idtt 관리 선생님 평가 총 수
     * @param idAdminIdttManageVO
     * @return
     */
    int selectReportsListCount(IdAdminIdttManageVO idAdminIdttManageVO);

    /**
     * DB 내 학교 목록 조회
     * @return
     */
    List<HashMap<String, String>> selectSchulList();
}
