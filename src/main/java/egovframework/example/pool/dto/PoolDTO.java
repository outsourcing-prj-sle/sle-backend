package egovframework.example.pool.dto;

import egovframework.example.pool.service.PoolManageVO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PoolDTO {
	private String pollId;
	private String pollNm;
	private String status;
	private String startDate;
	private String endDate;
	private Boolean expired;
	
	public PoolDTO(PoolManageVO poolManageVO) {
		this.setPollId(poolManageVO.getPollId());
		this.setPollNm(poolManageVO.getPollNm());		
		this.setStartDate(poolManageVO.getStartDate());
		this.setEndDate(poolManageVO.getEndDate());
		this.setStatus(poolManageVO.getStatus());
		this.setExpired(poolManageVO.getExpired());
	}
}
