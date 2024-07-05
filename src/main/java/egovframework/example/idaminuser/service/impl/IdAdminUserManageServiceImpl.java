package egovframework.example.idaminuser.service.impl;

import egovframework.example.cmmn.CustomException;
import egovframework.example.cmmn.utils.EncryptUtils;
import egovframework.example.idaminuser.dto.IdAdminUserDTO;
import egovframework.example.idaminuser.dto.IdAdminUserListDTO;
import egovframework.example.idaminuser.service.IdAdminUserManageService;
import egovframework.example.idaminuser.service.IdAdminUserManageVO;
import org.apache.commons.lang3.StringUtils;
import org.egovframe.rte.fdl.cmmn.exception.FdlException;
import org.egovframe.rte.fdl.idgnr.EgovIdGnrService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;

@Service("idAdminUserManageService")
public class IdAdminUserManageServiceImpl implements IdAdminUserManageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(IdAdminUserManageServiceImpl.class);

    @Resource(name = "idAdminUserManageMapper")
    private IdAdminUserManageMapper mapper;

    @Resource(name = "userIdGnrService")
    private EgovIdGnrService userIdGnrService;


    @Override
    public void insertIdAdminUserInfo(IdAdminUserManageVO idAdminUserManageVO) {
        try {
            idAdminUserManageVO.setUniqId(userIdGnrService.getNextStringId());
            idAdminUserManageVO.setPassword(EncryptUtils.getSHA256(idAdminUserManageVO.getPassword()));
        } catch(Exception e) {
            LOGGER.error(e.getMessage());
            throw new CustomException("사용자 고유 아이디 생성에 실패하였습니다.");
        }

        mapper.insertIdAdminUserInfo(idAdminUserManageVO);
    }

    @Override
    public void updateIdAdminUserInfo(IdAdminUserManageVO idAdminUserManageVO) {
        IdAdminUserDTO dto = mapper.selectIdAdminUserInfo(idAdminUserManageVO);

        mapper.updateIdAdminUserInfo(IdAdminUserManageVO.builder()
                        .uniqId(idAdminUserManageVO.getUniqId())
                        .userId(idAdminUserManageVO.getUserId())
                        .password(StringUtils.isEmpty(idAdminUserManageVO.getPassword())
                                ? mapper.selectIdAdminUserPassword(idAdminUserManageVO.getUniqId())
                                : EncryptUtils.getSHA256(idAdminUserManageVO.getPassword()))
                        .userNm(StringUtils.isEmpty(idAdminUserManageVO.getUserNm())
                                ? dto.getUserNm() : idAdminUserManageVO.getUserNm())
                        .userId2(idAdminUserManageVO.getUserId2())
                        .userSeCode(StringUtils.isEmpty(idAdminUserManageVO.getUserSeCode())
                                ? dto.getUserSeCode() : idAdminUserManageVO.getUserSeCode())
                        .userSpaceInfo(StringUtils.isEmpty(idAdminUserManageVO.getUserSpaceInfo())
                                ? dto.getUserSpaceInfo() : idAdminUserManageVO.getUserSpaceInfo())
                        .emailAdres(StringUtils.isEmpty(idAdminUserManageVO.getEmailAdres())
                                ? dto.getEmailAdres() : idAdminUserManageVO.getEmailAdres())
                        .phoneNumber(StringUtils.isEmpty(idAdminUserManageVO.getPhoneNumber())
                                ? dto.getPhoneNumber() : idAdminUserManageVO.getPhoneNumber())
                        .build());
    }

    @Override
    public void deleteIdAdminUserInfo(IdAdminUserManageVO idAdminUserManageVO) {
        mapper.deleteIdAdminUserInfo(idAdminUserManageVO);
    }

    @Override
    public IdAdminUserListDTO selectIdAdminUserInfoList(IdAdminUserManageVO idAdminUserManageVO) {

        return IdAdminUserListDTO.builder()
                .userInfoList(mapper.selectIdAdminUserInfoList(idAdminUserManageVO))
                .pageNo(idAdminUserManageVO.getPageNo())
                .recordCount(idAdminUserManageVO.getRecordCount())
                .totalCount(mapper.selectIdAdminUserInfoCount())
                .build();
    }

    @Override
    public IdAdminUserDTO selectIdAdminUserInfo(IdAdminUserManageVO idAdminUserManageVO) {
        return mapper.selectIdAdminUserInfo(idAdminUserManageVO);
    }

    @Override
    public HashMap<String, String> selectIdAdminUserUniqId(IdAdminUserManageVO idAdminUserManageVO) {
        return mapper.selectIdAdminUserUniqId(idAdminUserManageVO);
    }

    @Override
    public boolean isSignUpUser(IdAdminUserManageVO idAdminUserManageVO) {
        idAdminUserManageVO.setPassword(EncryptUtils.getSHA256(idAdminUserManageVO.getPassword()));
        return mapper.isSignUpUser(idAdminUserManageVO);
    }

    @Override
    public boolean isAuthorizedUser(IdAdminUserManageVO idAdminUserManageVO) {
        return mapper.isAuthorizedUser(idAdminUserManageVO);
    }

    @Override
    public int selectIdAdminUserInfoCount() {
        return mapper.selectIdAdminUserInfoCount();
    }
}
