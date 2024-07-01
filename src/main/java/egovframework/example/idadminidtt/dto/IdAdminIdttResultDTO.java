package egovframework.example.idadminidtt.dto;

import lombok.*;

import java.util.ArrayList;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IdAdminIdttResultDTO {
    private String schulNm;
    private String userNm;
    private String userSpaceOrgInfo;
    private ArrayList<?> qesAnswer;
}
