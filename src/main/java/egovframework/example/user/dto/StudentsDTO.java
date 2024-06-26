package egovframework.example.user.dto;

import lombok.*;

@Data
@Builder
public class StudentsDTO {
	private String pollNm;
	private String pollId;
	private String startDate;
	private String endDate;
	private String status;
	private Boolean expired;
}