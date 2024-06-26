package egovframework.example.user.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReportDTO {
    private String pollNm;
    private String startDate;
    private String endDate;
    private String expired;
}
