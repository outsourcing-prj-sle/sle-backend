package egovframework.example.poll.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PollNoticeDTO {
	private String nttNo;
	/**
	 * 고유ID
	 */
	private String Authorization;
	
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
