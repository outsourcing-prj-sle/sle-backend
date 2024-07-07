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
public class SubCommonCodesDTO extends PaginationVO{
    private String subCodeId;
    private String codeId;
    private String subCodeName;
    private String codeDescription;
    private String createdAt;
    private String updatedAt;
    private String createdBy;
}
