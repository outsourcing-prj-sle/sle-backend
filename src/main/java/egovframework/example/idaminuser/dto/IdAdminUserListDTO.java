package egovframework.example.idaminuser.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IdAdminUserListDTO {
    private List<IdAdminUserDTO> userInfoList;
    private int pageNo = 1;
    private int limit = 10;
    private int totalCount;
}
