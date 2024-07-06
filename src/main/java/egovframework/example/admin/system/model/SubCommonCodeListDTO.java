package egovframework.example.admin.system.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubCommonCodeListDTO {
    private List<SubCommonCodesDTO> userInfoList;
    private int pageNo = 1;
    private int recordCount = 10;
    private int totalCount;
}
