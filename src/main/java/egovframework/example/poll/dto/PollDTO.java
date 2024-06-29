package egovframework.example.poll.dto;

import egovframework.example.poll.service.PollManageVO;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PollDTO {
	private String nttNo;
	private String pollId;
	private String pollNm;
	private String status;
	private String startDate;
	private String endDate;
	private Boolean expired;
}
