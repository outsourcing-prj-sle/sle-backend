package egovframework.example.admin.system.model;

import egovframework.example.cmmn.service.PaginationVO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SiteManageDTO extends PaginationVO{
    private String siteId;
    private String siteName;
    private String siteDomain;
    private String createdAt;
    private String updatedAt;
    private String topLogoImage;
    private String bottomLogoImage;
    private String mainLogoImage;
    private String siteDescription;
    private boolean mouseSecurity;
    private boolean keyboardSecurity;
    private String createdBy;
}
