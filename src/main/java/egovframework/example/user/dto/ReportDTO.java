package egovframework.example.user.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReportDTO {
    private String pollNm;
    private String startDate;
    private String endDate;
    private String expired;
}
