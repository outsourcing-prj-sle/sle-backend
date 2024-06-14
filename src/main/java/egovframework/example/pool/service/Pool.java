package egovframework.example.pool.service;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pool {
	private String pollId;
	private String pollNm;
	private String status;
	private String startDate;
	private String endDate;
	private Boolean expired;
	
	public Pool(PoolManageVO poolManageVO) {
		this.setPollId(poolManageVO.getPollId());
		this.setPollNm(poolManageVO.getPollNm());		
		this.setStartDate(poolManageVO.getStartDate());
		this.setEndDate(poolManageVO.getEndDate());
		this.setStatus(poolManageVO.getStatus());
		this.setExpired(poolManageVO.getExpired());
	}
}
