package egovframework.example.user.service;

import egovframework.example.cmmn.service.LoginVO;
import egovframework.example.naver.dto.NaverTokenDto;
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
public class Users {
	private String id;
	private String name;
	private String profileImageId;
	private String userType;
	private String userSpaceInfo;
	private String userSpaceOrgInfo;
	private String uniqId;
	private String relationInfo;
	private Boolean isFirstInvite;
	private String sex;
	private String userEmail;
	private String brthdy;
	private String accessToken;
}
