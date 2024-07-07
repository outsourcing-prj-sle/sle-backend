package egovframework.example.idaminuser.service;

import egovframework.example.idaminuser.dto.IdAdminUserDTO;
import egovframework.example.idaminuser.dto.IdAdminUserListDTO;
import org.egovframe.rte.fdl.cmmn.exception.FdlException;

import java.util.HashMap;

public interface IdAdminUserManageService {
    /**
     * ID ADMIN 사용자 정보 등록
     * @param idAdminUserManageVO
     */
    void insertIdAdminUserInfo(IdAdminUserManageVO idAdminUserManageVO) throws FdlException;

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
    IdAdminUserListDTO selectIdAdminUserInfoList(IdAdminUserManageVO idAdminUserManageVO);

    /**
     * ID ADMIN 사용자 정보 조회
     * @param idAdminUserManageVO
     * @return
     */
    IdAdminUserDTO selectIdAdminUserInfo(IdAdminUserManageVO idAdminUserManageVO);

    /**
     * ID ADMIN 사용자 uniqId 조회
     * @param idAdminUserManageVO
     * @return
     */
    HashMap<String, String> selectIdAdminUserUniqId(IdAdminUserManageVO idAdminUserManageVO);

    /**
     * ID ADMIN 회원가입 확인
     * @param idAdminUserManageVO
     * @return
     */
    boolean isSignUpUser(IdAdminUserManageVO idAdminUserManageVO);

    /**
     * ID ADMIN 사용자 확인
     * @param idAdminUserManageVO
     * @return
     */
    boolean isAuthorizedUser(IdAdminUserManageVO idAdminUserManageVO);
}
