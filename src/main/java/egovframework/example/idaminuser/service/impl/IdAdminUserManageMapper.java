package egovframework.example.idaminuser.service.impl;

import egovframework.example.idaminuser.dto.IdAdminUserDTO;
import egovframework.example.idaminuser.service.IdAdminUserManageVO;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import java.util.List;

@Mapper("idAdminUserManageMapper")
public interface IdAdminUserManageMapper {
    /**
     * ID ADMIN 사용자 정보 등록
     * @param idAdminUserManageVO
     */
    void insertIdAdminUserInfo(IdAdminUserManageVO idAdminUserManageVO);

    /**
     * ID ADMIN 사용자 정보 수정
     * @param idAdminUserManageVO
     */
    void updateIdAdminUserInfo(IdAdminUserManageVO idAdminUserManageVO);

    /**
     * ID ADMIN 사용자 정보 삭제
     * @param idAdminUserManageVO
     */
    void deleteIdAdminUserInfo(IdAdminUserManageVO idAdminUserManageVO);

    /**
     * ID ADMIN 사용자 목록 조회
     * @param idAdminUserManageVO
     * @return
     */
    List<IdAdminUserDTO> selectIdAdminUserInfoList(IdAdminUserManageVO idAdminUserManageVO);

    /**
     * ID ADMIN 사용자 정보 조회
     * @param idAdminUserManageVO
     * @return
     */
    IdAdminUserDTO selectIdAdminUserInfo(IdAdminUserManageVO idAdminUserManageVO);

    /**
     * ID ADMIN 사용자 확인
     * @param idAdminUserManageVO
     * @return
     */
    boolean isAuthorizedUser(IdAdminUserManageVO idAdminUserManageVO);

    /**
     * ID ADMIn 사용자 목록 총 수
     * @return
     */
    int selectIdAdminUserInfoCount();
}
