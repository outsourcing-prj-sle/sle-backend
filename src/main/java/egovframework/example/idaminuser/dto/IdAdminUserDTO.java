package egovframework.example.idaminuser.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IdAdminUserDTO {
    private String uniqId;
    private String userId;
    private String userSeCode;
    private String userNm;
    private String emailAdres;
    private String phoneNumber;
    private String userSpaceInfo;
    private String frstRegistPnttm;
}
