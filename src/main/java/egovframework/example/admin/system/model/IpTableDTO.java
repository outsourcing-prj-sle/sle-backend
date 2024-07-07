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
public class IpTableDTO extends PaginationVO{
    private String allowedIp;
    private String adminId;
    private boolean isActive;
    private String createdAt;
    private String updatedAt;
    private String createdBy;
}
