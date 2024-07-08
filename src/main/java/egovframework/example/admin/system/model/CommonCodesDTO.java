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
public class CommonCodesDTO extends PaginationVO{
    private String codeId;
    private String categoryCode;
    private String codeName;
    private String codeDescription;
    private int subCount;
    private boolean isActive;
    private String createdAt;
    private String updatedAt;
    private String createdBy;
}
