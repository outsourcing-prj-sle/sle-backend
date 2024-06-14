package egovframework.example.login.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import egovframework.example.cmmn.service.AccountDTO;
import egovframework.example.cmmn.service.LoginVO;
import egovframework.example.cmmn.service.ResultVO;
import egovframework.example.login.service.LoginManageService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/login")
public class LoginManageController {
	
	@Autowired
	private LoginManageService loginManageService;
	
	/**
	 * 기본 로그인
	 * @param AccountDTO
	 */
	@PostMapping("/")
	public ResponseEntity<?> selLogin(@RequestBody AccountDTO account){
		LoginVO user = new LoginVO();
		try {
			user = loginManageService.selectUserById(account);
			if(user.getPassword()==account.getPassword()) {
				log.info("로그인 User : ",account.getId());
			}

		}catch(Exception e) {
			log.error("로그인 에러",e);
			return null;
		}
		return ResponseEntity.ok(ResultVO.res(HttpStatus.OK,HttpStatus.OK.toString(),user));
	}
	
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
