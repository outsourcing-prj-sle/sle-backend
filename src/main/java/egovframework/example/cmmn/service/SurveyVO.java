package egovframework.example.cmmn.service;

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
public class SurveyVO {
	/**
	 * 학생 아이디
	 */
	private String id;

	/**
	 * 선생님 아이디
	 */
	private String teacherId;

	/**
	 * 설문조사 타입
	 * SE : 사회정서, LT : 학습성향
	 */
	private String qesType = "SE";

	/**
	 * 설문조사 답변
	 */
	private String qesAnswer;
}
