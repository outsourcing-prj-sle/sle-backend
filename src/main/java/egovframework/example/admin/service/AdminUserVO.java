package egovframework.example.admin.service;

import egovframework.example.cmmn.service.AdminLoginVO;
import egovframework.example.cmmn.service.PaginationVO;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminUserVO extends PaginationVO{
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
	 * 역할
	 */
	private String userRole;
	
	/**
	 * 학년
	 */
	private String gradeNm;
	/**
	 * 반
	 */
	private String classNm;
	
	/**
	 * 전화번호
	 */
	private String phoneNumber;
	
	
	private String startDate;
	private String endDate;
}
