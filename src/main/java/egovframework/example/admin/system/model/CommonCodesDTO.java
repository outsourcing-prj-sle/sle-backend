package egovframework.example.admin.system.model;

import lombok.Data;

@Data
public class CommonCodesDTO {
    private String codeId;
    private String categoryCode;
    private String codeName;
    private String codeDescription;
    private char active;
    private String createdAt;
    private String updatedAt;
    private String createdBy;
}
