package egovframework.example.cmmn.service;

import lombok.*;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginVO {

	/**
	 * 사용자 ID
	 */
	private String authorization;

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
	 * 사용자 구분(04: 학생, 08: 교사)
	 */
	private String userType;
	
	/**
	 * 학교명
	 */
	private String userSpaceInfo;
	
	/**
	 * 소속 조직 정보(ex: 1학년 1반)
	 */
	private String userSpaceOrgInfo;

	/**
	 * 학교 코드
	 */
	private String schulCode;

	/**
	 * 학교급 코드
	 */
	private String schulGradeCode;
	
	/**
	 * 사용자 성별
	 */
	private String sex;
	
	/**
	 * 사용자 이메일
	 */
	private String userEmail;

	/**
	 * 기준년도
	 */
	private String stYear;
	
	/**
	 * 학년
	 */
	private String gradeNm;

	/**
	 * 반
	 */
	private String classCode;

	/**
	 * 반 이름
	 */
	private String classNm;

	/**
	 * 번호
	 */
	private String stNumber;
	
	/**
	 * 역할
	 */
	private String userRole;

	/**
	 * 권한코드
	 */
	private String authorCode;
	
	/**
	 * 생년월일
	 */
	private String brthdy;
	
	/**
	 * 선생님 구분 코드
	 */
	private Boolean isTeacher;

	public String getAuthorCode() {
		if(this.userType.equals("04")) {
			this.setAuthorCode("ROLE_STUDENT");
		}

		if(this.userType.equals("08")) {
			this.setAuthorCode("ROLE_TEACHER");
		}

		return authorCode;
	}

	public String getUserRole() {
		if(this.userType.equals("04")) {
			this.setUserRole("student");
		}

		if(this.userType.equals("08")) {
			this.setUserRole("teacher");
		}

		return userRole;
	}
}
