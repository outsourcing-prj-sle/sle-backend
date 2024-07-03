package egovframework.example.admin.system.model;

import lombok.Data;

@Data
public class SubCommonCodesDTO {
    private String subCodeId;
    private String codeId;
    private String subCodeName;
    private String codeDescription;
    private String createdAt;
    private String updatedAt;
    private String createdBy;
}
