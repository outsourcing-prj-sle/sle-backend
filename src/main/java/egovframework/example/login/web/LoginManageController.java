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
			return ResponseEntity.ok(ResultVO.res(HttpStatus.OK,"Login Failed",user));
		}
		return ResponseEntity.ok(ResultVO.res(HttpStatus.OK,HttpStatus.OK.toString(),user));
	}
	
	/**
	 * 네이버 로그인
	 */
	@GetMapping("/naver")
	public ResponseEntity<?> naverLogin() {
		try {
		}catch(Exception e) {
			log.error("로그인 에러",e);
			return ResponseEntity.ok(ResultVO.res(HttpStatus.OK,"Naver Login Failed"));
		}
		return ResponseEntity.ok(ResultVO.res(HttpStatus.OK,HttpStatus.OK.toString()));
	}
	
	
	/**
	 * 카카오 로그인
	 */
	@GetMapping("/kakao")
	public ResponseEntity<?> kakaoLogin() {
		try {
		}catch(Exception e) {
			log.error("로그인 에러",e);
			return ResponseEntity.ok(ResultVO.res(HttpStatus.OK,"Kakao Login Failed"));
		}
		return ResponseEntity.ok(ResultVO.res(HttpStatus.OK,HttpStatus.OK.toString()));
	}
	
	/**
	 * 구글 로그인
	 */
	@GetMapping("/google")
	public ResponseEntity<?> googleLogin() {
		try {
		}catch(Exception e) {
			log.error("로그인 에러",e);
			return ResponseEntity.ok(ResultVO.res(HttpStatus.OK,"Google Login Failed"));
		}
		return ResponseEntity.ok(ResultVO.res(HttpStatus.OK,HttpStatus.OK.toString()));
	}
	
	/**
	 * 경남교육청 로그인
	 */
	@GetMapping("/gne")
	public ResponseEntity<?> gneLogin() {
		try {
		}catch(Exception e) {
			log.error("로그인 에러",e);
			return ResponseEntity.ok(ResultVO.res(HttpStatus.OK,"Whale Login Failed"));
		}
		return ResponseEntity.ok(ResultVO.res(HttpStatus.OK,HttpStatus.OK.toString()));
	}
	
	/**
	 * 최초 회원 가입 시 필수 제공 정보 동의
	 */
	@PutMapping("/required-agree-info")
	public ResponseEntity<?> insertRequiredAgreeInfo(){
		try {
			LoginVO loginVO = new LoginVO();
			loginVO.setUniqId("USRCNFRM_00000000004");
			
			loginManageService.updateRequiredAgreeInfo(loginVO);
		}catch(Exception e) {
			log.error("API 에러",e);
			return ResponseEntity.ok(ResultVO.res(HttpStatus.OK,"Required Agree Failed"));
		}
		return ResponseEntity.ok(ResultVO.res(HttpStatus.OK,HttpStatus.OK.toString()));
	}
	
}
