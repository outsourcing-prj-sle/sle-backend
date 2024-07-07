package egovframework.example.admin.system.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SiteListDTO {
    private List<SiteManageDTO> siteInfoList;
    private int pageNo = 1;
    private int limit = 10;
    private int totalCount;
}
