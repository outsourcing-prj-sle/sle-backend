package egovframework.example.poll.dto;

import egovframework.example.poll.service.PollManageVO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PollDTO {
	private String pollId;
	private String pollNm;
	private String status;
	private String startDate;
	private String endDate;
	private Boolean expired;
	
	public PollDTO(PollManageVO pollManageVO) {
		this.setPollId(pollManageVO.getPollId());
		this.setPollNm(pollManageVO.getPollNm());
		this.setStartDate(pollManageVO.getStartDate());
		this.setEndDate(pollManageVO.getEndDate());
		this.setStatus(pollManageVO.getStatus());
		this.setExpired(pollManageVO.getExpired());
	}
}
