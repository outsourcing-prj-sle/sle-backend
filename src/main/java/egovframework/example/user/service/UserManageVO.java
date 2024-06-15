package egovframework.example.user.service;

import org.springmodules.validation.bean.conf.loader.annotation.handler.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserManageVO {

	/**
	 * 사용자 id
	 */
	@NotEmpty(message = "ID is required")
	private String id;
	
	/**
	 * 사용자명
	 */
	@NotEmpty(message = "ID is required")
	private String name;
	
	/**
	 * 비밀번호
	 */
	@NotEmpty(message = "ID is required")
	private String password;
	
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
	
}
