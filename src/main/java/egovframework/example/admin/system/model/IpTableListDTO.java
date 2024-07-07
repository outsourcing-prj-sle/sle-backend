package egovframework.example.admin.system.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IpTableListDTO {
    private List<IpTableDTO> ipInfoList;
    private int pageNo = 1;
    private int limit = 10;
    private int totalCount;
}
