package egovframework.example.user.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentsDTO {
	private int nttNo;
	private String pollNm;
	private String pollId;
	private String startDate;
	private String endDate;
	private String status;
	private Boolean expired;
}