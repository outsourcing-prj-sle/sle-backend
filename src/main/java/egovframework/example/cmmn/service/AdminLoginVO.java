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
public class AdminLoginVO {
	
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
	 * 소속 조직 정보
	 */
	private String userSpaceOrgInfo;
	
	/**
	 * 고유 번호
	 */
	private String uniqId;
	
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
	 * 전화번호
	 */
	private String phoneNumber;
	
	/**
	 * 등록 시간
	 */
	private String createAt;
	
	/**
	 * 업데이트 시간
	 */
	private String updateAt;
	
}
