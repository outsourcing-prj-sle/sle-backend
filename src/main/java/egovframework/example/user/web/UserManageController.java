package egovframework.example.user.web;


import java.util.HashMap;
import java.util.Map;

import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import egovframework.example.cmmn.service.LoginVO;
import egovframework.example.cmmn.service.ResultVO;
import egovframework.example.user.service.UserManageService;
import egovframework.example.user.service.Users;

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
		
		Users res = new Users(userInfo);
		
		return ResponseEntity.ok(ResultVO.res(HttpStatus.OK,HttpStatus.OK.toString(),res));
	}
	
	/**
	 * 회원정보 등록
	 */
	@PutMapping("/users/insert")
	ResponseEntity<?> insertUserInfo(@RequestBody LoginVO loginVO) {
		try {
		loginVO.setUniqId("USRCNFRM_00000000004");
		userManageService.insertUserInfo(loginVO);
		}catch(Exception e) {
			return ResponseEntity.ok(ResultVO.res(HttpStatus.NOT_FOUND,HttpStatus.NOT_FOUND.toString(),"Data Not Found"));
		}
		
		return ResponseEntity.ok(ResultVO.res(HttpStatus.OK,HttpStatus.OK.toString(),"Sign In Success"));
	}
	
	/**
	 * 회원정보 수정
	 */
	@PutMapping("/users/update")
	ResponseEntity<?> updateUserInfo(@RequestBody LoginVO loginVO) {
		loginVO.setUniqId("USRCNFRM_00000000004");
		
		userManageService.updateUserInfo(loginVO);
		
		return ResponseEntity.ok(ResultVO.res(HttpStatus.OK,HttpStatus.OK.toString(),"User update Success"));
	}
	/**
	 * 회원 SEL 알기 목록 조회
	 * @return 
	 */
}