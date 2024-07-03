package egovframework.example.admin.system.model;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class TermsManageDTO {
    private String termsId;
    private String studentTerms;
    private String staffTerms;
    private String createdAt;
    private String updatedAt;
    private String effectiveDate;
    private String createdBy;
}
