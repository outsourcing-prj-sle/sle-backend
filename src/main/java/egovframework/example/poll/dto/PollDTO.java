package egovframework.example.poll.dto;

import egovframework.example.poll.service.PollManageVO;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
public class PollDTO {
	private String pollId;
	private String pollNm;
	private String status;
	private String startDate;
	private String endDate;
	private Boolean expired;
}
