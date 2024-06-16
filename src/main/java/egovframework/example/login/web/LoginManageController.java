package egovframework.example.login.web;

import java.util.HashMap;

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
@RequestMapping("/api")
public class LoginManageController {
	
	@Autowired
	private LoginManageService loginManageService;
	
	/**
	 * 기본 로그인
	 * @param AccountDTO
	 */
	@PostMapping("/login")
	public ResponseEntity<?> selLogin(@RequestBody AccountDTO account){
		LoginVO user = new LoginVO();
		log.info("로그인 User : {} , {} ",account.getId(),account.getPassword());
		try {
			user = loginManageService.selectUserById(account);
			System.out.println(user.getPassword().length());
			System.out.println(account.getPassword().length());
			if(!user.getPassword().equals(account.getPassword())) {
				log.error("로그인 에러");
				return ResponseEntity.ok(ResultVO.res(HttpStatus.OK,"Login Failed(Wrong Password)"));
			}

		}catch(Exception e) {
			log.error("로그인 에러");
			return ResponseEntity.ok(ResultVO.res(HttpStatus.OK,"Login Failed(User Not Found)"));
		}
		log.info("로그인 User : ",account.getId());
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		HashMap<String, String> obj = new HashMap<String, String>();
		obj.put("spaceInfo", "COM1");
		obj.put("grade", "1");
		obj.put("class", "3");
		
		result.put("token", user.getUniqId());
		result.put("id", account.getId());
		result.put("type", "student");
		result.put("school", "test");
		result.put("extra", obj);
		
//		return ResponseEntity.ok(ResultVO.res(HttpStatus.OK,HttpStatus.OK.toString(),user));
		return ResponseEntity.ok(result);
	}
	
	/**
	 * 네이버 로그인
	 */
	@GetMapping("/login/naver")
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
	@GetMapping("/login/kakao")
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
	@GetMapping("/login/google")
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
	@GetMapping("/login/gne")
	public ResponseEntity<?> gneLogin() {
		try {
		}catch(Exception e) {
			log.error("로그인 에러",e);
			return ResponseEntity.ok(ResultVO.res(HttpStatus.OK,"Whale Login Failed"));
		}
		return ResponseEntity.ok().build();
	}
	
	/**
	 * 최초 회원 가입 시 필수 제공 정보 동의
	 */
	@PutMapping("/login/required-agree-info")
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
