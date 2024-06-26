package egovframework.example.user.dto;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;

@Data
@Builder
public class MySelTeacherResultDTO {
    private ArrayList<TeachersDTO> infoArr;
    private ArrayList<ReportDTO> reportList;
}
