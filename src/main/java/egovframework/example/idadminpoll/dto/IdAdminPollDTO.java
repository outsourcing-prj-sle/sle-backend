package egovframework.example.idadminpoll.dto;

import lombok.*;

import java.util.ArrayList;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IdAdminPollDTO {
    private String pollNm;
    private String pollBgnde;
    private String pollEndde;
    private ArrayList<String> pollTarget;
    private int pageNo;
    private int recordCount;
    private int totalCount;
}
