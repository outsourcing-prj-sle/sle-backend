package egovframework.example.idaminuser.service.impl;

import egovframework.example.idadminidtt.service.IdAdminIdttManageService;
import egovframework.example.idaminuser.dto.IdAdminUserDTO;
import egovframework.example.idaminuser.dto.IdAdminUserListDTO;
import egovframework.example.idaminuser.service.IdAdminUserManageService;
import egovframework.example.idaminuser.service.IdAdminUserManageVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("idAdminUserManageService")
public class IdAdminUserManageServiceImpl implements IdAdminUserManageService {

    @Resource(name = "idAdminUserManageMapper")
    private IdAdminUserManageMapper mapper;


    @Override
    public void insertIdAdminUserInfo(IdAdminUserManageVO idAdminUserManageVO) {
        mapper.insertIdAdminUserInfo(idAdminUserManageVO);
    }

    @Override
    public void updateIdAdminUserInfo(IdAdminUserManageVO idAdminUserManageVO) {
        IdAdminUserDTO dto = mapper.selectIdAdminUserInfo(idAdminUserManageVO);

        mapper.updateIdAdminUserInfo(IdAdminUserManageVO.builder()
                        .userId(idAdminUserManageVO.getUserId())
                        .userNm(StringUtils.isEmpty(idAdminUserManageVO.getUserNm())
                                ? dto.getUserNm() : idAdminUserManageVO.getUserNm())
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
    public boolean isAuthorizedUser(IdAdminUserManageVO idAdminUserManageVO) {
        return mapper.isAuthorizedUser(idAdminUserManageVO);
    }

    @Override
    public int selectIdAdminUserInfoCount() {
        return mapper.selectIdAdminUserInfoCount();
    }
}
