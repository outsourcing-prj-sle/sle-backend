package egovframework.example.login.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import egovframework.example.cmmn.service.LoginVO;
import egovframework.example.login.service.LoginManageService;

@RestController
@RequestMapping("/login")
public class LoginManageController {
	
	@Autowired
	private LoginManageService loginManageService;
	
	/**
	 * 네이버 로그인
	 */
	@GetMapping("/naver")
	public ResponseEntity<?> naverLogin() {
		
		return ResponseEntity.ok().build();
	}
	
	
	/**
	 * 카카오 로그인
	 */
	@GetMapping("/kakao")
	public ResponseEntity<?> kakaoLogin() {
		
		return ResponseEntity.ok().build();
	}
	
	/**
	 * 구글 로그인
	 */
	@GetMapping("/google")
	public ResponseEntity<?> googleLogin() {
		
		return ResponseEntity.ok().build();
	}
	
	/**
	 * 경남교육청 로그인
	 */
	@GetMapping("/gne")
	public ResponseEntity<?> gneLogin() {
		
		return ResponseEntity.ok().build();
	}
	
	/**
	 * 최초 회원 가입 시 필수 제공 정보 동의
	 */
	@PutMapping("/required-agree-info")
	public ResponseEntity<?> insertRequiredAgreeInfo(){
		
		LoginVO loginVO = new LoginVO();
		loginVO.setUniqId("USRCNFRM_00000000004");
		
		loginManageService.updateRequiredAgreeInfo(loginVO);
		
		return ResponseEntity.ok().build();
	}
	
}
