package egovframework.example.user.dto;

import java.util.HashMap;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class TeachersDTO {
	private String userId = "";
	private String name;
	private String email;
	private String classInfo;
	private String sex = "F";
	private HashMap<String, Integer> stateList = new HashMap<String, Integer>() {{
		put("마음알기 설문1", 0);
		put("마음알기 설문2", 0);
		put("마음알기 설문3", 0);
		put("마음알기 설문4", 0);
		put("마음알기 설문5", 0);
		put("마음알기 설문6", 0);
	}};
	private HashMap<String, String> stateFinList = new HashMap<>();
}