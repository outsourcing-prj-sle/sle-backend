package egovframework.example.user.service;

import egovframework.example.cmmn.service.LoginVO;
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
	
	public Users(LoginVO loginVO) {
		this.setId(loginVO.getId());
		this.setName(loginVO.getName());
		this.setProfileImageId(loginVO.getProfileImageId());
		this.setUserType(loginVO.getUserType());
		this.setUserSpaceInfo(loginVO.getUserSpaceInfo());
		this.setUserSpaceOrgInfo(loginVO.getUserSpaceOrgInfo());
		this.setUniqId(loginVO.getUniqId());
		this.setRelationInfo(loginVO.getRelationInfo());
		this.setIsFirstInvite(loginVO.getIsFirstInvite());
		this.setSex(loginVO.getSex());
		this.setUserEmail(loginVO.getUserEmail());
	}
}
