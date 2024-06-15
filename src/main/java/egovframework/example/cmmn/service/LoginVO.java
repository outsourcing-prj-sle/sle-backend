package egovframework.example.cmmn.service;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginVO {
	
	/**
	 * 사용자 id
	 */
	private String id;
	
	/**
	 * 사용자 비밀번호
	 */
	private String password;
	
	/**
	 * 사용자명
	 */
	private String name;
	
	/**
	 * 프로필 이미지 아이디
	 */
	private String profileImageId;
	
	/**
	 * 사용자 구분
	 */
	private String userType;
	
	/**
	 * 소속 스페이스 정보
	 */
	private String userSpaceInfo;
	
	/**
	 * 소속 조직 정보
	 */
	private String userSpaceOrgInfo;
	
	/**
	 * 고유 번호
	 */
	private String uniqId;
	
	/**
	 * 관계 정보
	 */
	private String relationInfo;
	
	/**
	 * 최초 로그인 여부
	 */
	private Boolean isFirstInvite;
	
	/**
	 * 사용자 성별
	 */
	private String sex;
	
	/**
	 * 사용자 이메일
	 */
	private String userEmail;
	
	/**
	 * 학년
	 */
	private String gradeNm;
	
	/**
	 * 반
	 */
	private String classNm;
	
	/**
	 * 역할
	 */
	private String userRole;
	
	/**
	 * 역할 배정
	 */
	public void isRole() {
		if(this.userType.equals("Y")) {
			this.setUserRole("ROLE_TEACHER");
		}
		
		if(this.userType.equals("N")) {
			this.setUserRole("ROLE_STUDENT");
		}
	}
}
