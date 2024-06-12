package egovframework.example.pool.service;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PoolManageVO {
	
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
	 * 임시저장여부
	 */
	private Boolean isSave;
	
	/**
	 * 음성지원여부
	 */
	private Boolean isVoice;
	
	/**
	 * 설문상태
	 */
	private String status;
	
	/**
	 * 문항순번
	 */
	private String qesitmSn;
	
	/**
	 * 문항순번배열
	 */
	private String qesitmSnList;
	
	/**
	 * 문항답변
	 */
	private String qesitmAnswer; 
	
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
	
	/**
	 * 문항 질문
	 */
	private String qesitmSj;
	
}
