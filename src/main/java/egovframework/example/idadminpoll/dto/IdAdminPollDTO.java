package egovframework.example.idadminpoll.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IdAdminPollDTO {
    private String pollNm;
    private String pollBgnde;
    private String pollEndde;
    private String pollTarget;
}
