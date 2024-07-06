package egovframework.example.admin.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import egovframework.example.admin.service.AdminManageService;
import egovframework.example.cmmn.service.AdminLoginVO;
import egovframework.example.cmmn.service.LoginVO;
import egovframework.example.user.service.UserManageService;

@RestController
@RequestMapping("/api/admin")
public class AdminManageController {
	
	@Resource(name = "adminManageService")
	private AdminManageService adminManageService;
	
	@Resource(name = "userManageService")
	private UserManageService userManageService;
	
	@GetMapping("/users/{role}/{id}")
	ResponseEntity<?> myInfo(@PathVariable String role, @PathVariable String id){
		if(role.equals("teacher") || role.equals("student")) {
			LoginVO user = LoginVO.builder().authorization(id).build();
			LoginVO whaleUsers = userManageService.selectUserInfo(user);
			return ResponseEntity.ok(whaleUsers);
		}
		AdminLoginVO user = AdminLoginVO.builder()
								.uniqId(id).build();
		AdminLoginVO res = adminManageService.selectUser(user);
		return ResponseEntity.ok(res);
	}
	/**
	 * 권한에 따른 회원 리스트 조회
	 * (OgzAdmin, SuperAdmin, SchoolAdmin)→Admin, OfficeOfEdu, Student, Teacher, NormalUser
	 * Query로 검색가능 예) OgzAdmin?name=홍길동
	 * @return
	 */
	@GetMapping("/users/{role}")
	ResponseEntity<?> selectAdminManagement(@PathVariable String role, @RequestParam(required=false) Map<String,String> conditions){
		conditions.put("userRole", role);
		if(conditions.get("userRole").equals("teacher") || conditions.get("userRole").equals("student")) {
			List<LoginVO> whaleUsers = userManageService.selectUserByConditions(conditions);
			return ResponseEntity.ok(whaleUsers);
		}
		List<AdminLoginVO> users = adminManageService.selectUserAll(conditions);
		return ResponseEntity.ok(users);
	}
	
	@PutMapping("/users/{role}/register")
	ResponseEntity<?> insertUser(@PathVariable String role, AdminLoginVO loginVO){
		switch(role.toLowerCase()) {
			case "student": 
			case "teacher": 
				LoginVO whaleUser = LoginVO.builder()
									.authorization(loginVO.getId())
									.name(loginVO.getName())
									.password(loginVO.getPassword())
									.userRole(role)
									.gradeNm(loginVO.getGradeNm())
									.classNm(loginVO.getClassNm())
									.userSpaceInfo(loginVO.getUserSpaceOrgInfo())
									.userEmail(loginVO.getUserEmail())
									.build();
				userManageService.insertUserInfo(whaleUser);
				return ResponseEntity.ok().build();
			case "ogzadmin":
			case "schooladmin":
			case "admin":
				loginVO.setUniqId(adminManageService.insertUser(loginVO));
				AdminLoginVO res = adminManageService.selectUser(loginVO);
				return ResponseEntity.ok(res);
			default:
				return ResponseEntity.ok("Register Failed");
				
		}

	}
	
	@DeleteMapping("/users/{role}/{id}")
	ResponseEntity<?> deleteUser(@PathVariable String role, @PathVariable String id){
		if(role.equals("teacher") || role.equals("student")) {
			//List<LoginVO> whaleUsers = userManageService;
			return ResponseEntity.ok().build();
		}
		String msg = adminManageService.deleteUser(id);
		return ResponseEntity.ok(msg);
	}
}