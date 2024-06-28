package egovframework.example.poll.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PollManageVO {
	
	/**
	 * 고유ID
	 */
	private String authorization;
	
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
	 * 문항답변
	 */
	private String qesitmAnswer;
	
	/**
	 * 문항답변이미지
	 */
	@Builder.Default
	private String quesitmAnswerImage = "0";
	
	/**
	 * 문항순번배열
	 */
	private String[] qesitmSnList;

	/**
	 * 문항답변배열
	 */
	private String[] qesitmAnswerList;

	/**
	 * 문항답변이미지배열
	 */
	private String[] qesitmAnswerImageList;
	
	/**
	 * 문항답변문자
	 */
	private String qesitmAnswerText;
	
	/**
	 * 문항답변이미지
	 */
	private String qesitmAnswerImage;
	
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

	public String[] getQesitmSnList() {
		return qesitmSn.split(",");
	}

	public String[] getQesitmAnswerList() {
		return qesitmAnswer.split(",");
	}

	public String[] getQesitmAnswerImageList() {
		return qesitmAnswerImage.split(",");
	}
}
