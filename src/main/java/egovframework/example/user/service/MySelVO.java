package egovframework.example.user.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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
	 * 설문조사_만료_여부_문자열
	 */
	private String expiredStr;
	
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
	 * 설문조사_참여_여부_문자열
	 */
	private String isParticipateStr;
	
	/**
	 * 최종_등록
	 */
	private String frstRegisterPnttm;

	/**
	 * 최종_등록_설문_ID
	 */
	private String pollIDForfrstRegisterPnttm;

	/**
	 * 설문조사_아이디_목록
	 */
	private String[] pollIdList;

	/**
	 * 설문조사_명칭_목록
	 */
	private String[] pollNmList;

	/**
	 * 설문조사_만료_여부_목록
	 */
	private String[] expiredList;

	/**
	 * 설문조사_참여_여부_목록
	 */
	private String[] isParticipateList;

	/**
	 * 시작_일자_목록
	 */
	private String[] startDateList;

	/**
	 * 종료_일자_목록
	 */
	private String[] endDateList;

	/**
	 * 최종_등록_목록
	 */
	private String[] frstRegisterPnttmList;

	/**
	 * 최종_등록_설문_ID_목록
	 */
	private String[] pollIDForfrstRegisterPnttmList;

	public String[] getPollIdList() {
		return pollId.split(",");
	}

	public String[] getPollNmList() {
		return pollNm.split(",");
	}

	public String[] getExpiredList() {
		return expiredStr.split(",");
	}

	public String[] getIsParticipateList() {
		return isParticipateStr.split(",");
	}

	public String[] getStartDateList() {
		return startDate.split(",");
	}

	public String[] getEndDateList() {
		return endDate.split(",");
	}

	public String[] getFrstRegisterPnttmList() {
		return frstRegisterPnttm.split(",");
	}

	public String[] getPollIDForfrstRegisterPnttmList() {
		return pollIDForfrstRegisterPnttm.split(",");
	}
}
