package egovframework.example.user.dto;

import java.util.HashMap;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeachersDTO {
	private String userId = "";
	private String name;
	private String email;
	private String classInfo;
	private String sex = "F";
	private HashMap<String, Integer> stateList;
	private HashMap<String, String> stateFinList;
}