package egovframework.example.admin.system.model;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class SiteManageDTO {
    private String siteId;
    private String siteName;
    private String siteDomain;
    private String createdAt;
    private String updatedAt;
    private String topLogoImage;
    private String bottomLogoImage;
    private String mainImage;
    private String siteDescription;
    private char mouseSecurity;
    private char keyboardSecurity;
    private String createdBy;
}
