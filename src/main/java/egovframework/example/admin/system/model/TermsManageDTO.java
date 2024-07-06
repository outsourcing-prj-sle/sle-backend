package egovframework.example.admin.system.model;

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
public class TermsManageDTO{
    private String termsId;
    private String studentTerms;
    private String staffTerms;
    private String createdAt;
    private String updatedAt;
    private String effectiveDate;
    private String createdBy;
}
