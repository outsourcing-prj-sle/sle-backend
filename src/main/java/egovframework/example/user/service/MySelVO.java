package egovframework.example.user.service;

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
public class MySelVO {
	
	/**
	 * 설문조사_아이디
	 */
	private String pollId;
	
	/**
	 * 설문조사_아이디_목록
	 */
	private String pollIdList;
	
	/**
	 * 설문조사_진행_상태
	 */
	private String status;
	
	/**
	 * 사용자_아이디
	 */
	private String userId;
	
	/**
	 * 설문조사_명칭
	 */
	private String pollNm;
	
	/**
	 * 설문조사_명칭_목록
	 */
	private String pollNmList;
	
	/**
	 * 설문조사_시작_일자
	 */
	private String startDate;
	
	/**
	 * 설문조사_종료_일자
	 */
	private String endDate;
	
	/**
	 * 설문조사_만료_여부
	 */
	private Boolean expired;
	
	/**
	 * 설문조사_만료_여부_목록
	 */
	private String expiredList;
	
	/**
	 * 사용자_명
	 */
	private String name;
	
	/**
	 * 사용자_이메일
	 */
	private String email;
	
	/**
	 * 학년_번호
	 */
	private String gradeNm;
	
	/**
	 * 반_번호
	 */
	private String classNm;
	
	/**
	 * 학년_반_정보
	 */
	private String classInfo;
	
	/**
	 * 사용자_성별
	 */
	private String sex;
	
	/**
	 * 설문조사_참여_여부
	 */
	private Boolean isParticipate;
	
	/**
	 * 설문조사_참여_여부_목록
	 */
	private String isParticipateList;
}
