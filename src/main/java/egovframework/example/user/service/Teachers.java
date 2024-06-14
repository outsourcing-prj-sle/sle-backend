package egovframework.example.user.service;

import java.util.HashMap;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Teachers {
	private String userId;
	private String name;
	private String email;
	private String classInfo;
	private String sex;
	private HashMap<String, Boolean> stateList;
}
