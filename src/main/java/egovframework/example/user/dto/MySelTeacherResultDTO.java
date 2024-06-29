package egovframework.example.user.dto;

import lombok.*;

import java.util.ArrayList;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MySelTeacherResultDTO {
    private ArrayList<TeachersDTO> infoArr;
    private ArrayList<ReportDTO> reportList;
}
