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
	private String id;
	private String teacherId;
	private String qesType;
	private String qesAnswer;
}
