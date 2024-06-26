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
public class IdTokTokVO {
	String qesitmAnswerType;
	String pollScore;
	String pollAvg;
	String pollStddev;
}
