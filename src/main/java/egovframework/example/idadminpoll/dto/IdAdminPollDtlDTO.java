package egovframework.example.idadminpoll.dto;

import lombok.*;

import java.util.ArrayList;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IdAdminPollDtlDTO {
    private String schulNm;
    private String stGrade;
    private String userNm;
    private String sexdstn;
    private String brthy;
    private String emailAdres;
    private ArrayList<String> answer;
}
