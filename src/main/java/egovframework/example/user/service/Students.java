package egovframework.example.user.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Students {
	private String pollNm;
	private String startDate;
	private String endDate;
	private String status;
	private Boolean expired;
}