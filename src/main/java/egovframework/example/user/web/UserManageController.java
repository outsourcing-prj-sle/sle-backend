package egovframework.example.user.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import egovframework.example.cmmn.service.LoginVO;
import egovframework.example.user.service.UserManageService;

@RestController
public class UserManageController {
	
	@Autowired
	private UserManageService userManageService;
	
	/**
	 * 회원정보 조회
	 */
	@GetMapping("/users")
	ResponseEntity<?> selectUserInfo() {
		LoginVO loginVO = new LoginVO();
		loginVO.setUniqId("USRCNFRM_00000000004");
		
		 LoginVO userInfo = userManageService.selectUserInfo(loginVO);
		 
		 ResponseEntity.ok(userInfo.getId());
		
		return ResponseEntity.ok(userInfo);
	}
	
	/**
	 * 마음알기 설문 목록 조회
	 */
	@GetMapping("/users/report-status")
	ResponseEntity<?> selectUserReportStatus() {
		
		return ResponseEntity.ok(null);
	}
	
	/**
	 * 마음알기 설문 상세 조회
	 */
	@GetMapping("/users/report-status/{id}")
	ResponseEntity<?> selectUserReportStatusDtl(@PathVariable("id") String id) {
		
		return ResponseEntity.ok(id);
	}
	
	/**
	 * 회원정보 등록
	 */
	@PutMapping("/users/insert")
	ResponseEntity<?> insertUserInfo() {
		LoginVO loginVO = new LoginVO();
		loginVO.setUniqId("USRCNFRM_00000000004");
		
		userManageService.insertUserInfo(loginVO);
		
		return ResponseEntity.ok().build();
	}
	
	/**
	 * 회원정보 수정
	 */
	@PutMapping("/users/update")
	ResponseEntity<?> updateUserInfo() {
		LoginVO loginVO = new LoginVO();
		loginVO.setUniqId("USRCNFRM_00000000004");
		
		userManageService.updateUserInfo(loginVO);
		
		return ResponseEntity.ok().build();
	}
	
}