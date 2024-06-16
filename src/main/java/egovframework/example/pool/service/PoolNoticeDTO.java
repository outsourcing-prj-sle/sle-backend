package egovframework.example.pool.service;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PoolNoticeDTO {
	/**
	 * 고유ID
	 */
	private String uniqId;
	
	/**
	 * 설문ID
	 */
	private String pollId;
	
	/**
	 * 설문명
	 */
	private String pollNm;
	
	/**
	 * 설문시작일자
	 */
	private String startDate;
	
	/**
	 * 설문종료일자
	 */
	private String endDate;
	
	/**
	 * 만료 여부
	 */
	private Boolean expired;
}
