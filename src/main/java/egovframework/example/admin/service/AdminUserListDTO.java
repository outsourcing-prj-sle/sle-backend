package egovframework.example.admin.service;

import java.util.List;

import egovframework.example.admin.system.model.SiteManageDTO;
import egovframework.example.cmmn.service.AdminLoginVO;
import egovframework.example.cmmn.service.LoginVO;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminUserListDTO {
    private List<AdminLoginVO> adminUserInfoList;
    private List<LoginVO> userInfoList;
    private int pageNo = 1;
    private int limit = 10;
    private int totalCount;
}
