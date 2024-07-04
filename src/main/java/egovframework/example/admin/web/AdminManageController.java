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

@RestController
@RequestMapping("/api/admin")
public class AdminManageController {
	
	@Resource(name = "adminManageService")
	private AdminManageService adminManageService;
	
	@GetMapping("/users")
	ResponseEntity<?> selectUser(HttpServletRequest request){
			AdminLoginVO user = adminManageService.selectUser((AdminLoginVO)request.getAttribute("user"));
			return ResponseEntity.ok(user);
	}
	/**
	 * 권한에 따른 회원 리스트 조회
	 * (OgzAdmin, SuperAdmin, SchoolAdmin) →　Admin, OfficeOfEdu, Student, Teacher, NormalUser
	 * Query로 검색가능 예) OgzAdmin?name=홍길동
	 * @return
	 */
	@GetMapping("/users/{role}")
	ResponseEntity<?> selectAdminManagement(@RequestHeader HttpHeaders header, @PathVariable String role, @RequestParam(required=false) Map<String,String> conditional){
		conditional.put("userRole", role);
		List<AdminLoginVO> users = adminManageService.selectUserAll(conditional);
		return ResponseEntity.ok(users);
	}
	
	@PutMapping("/users/register")
	ResponseEntity<?> insertUser(@RequestHeader HttpHeaders header,@RequestBody AdminLoginVO AdminLoginVO){
		AdminLoginVO.setUniqId(adminManageService.insertUser(AdminLoginVO));
		AdminLoginVO res = adminManageService.selectUser(AdminLoginVO);
		return ResponseEntity.ok(res);
	}
	
	@DeleteMapping("/users/delete")
	ResponseEntity<?> deleteUser(@RequestHeader HttpHeaders header, @RequestBody String deleteId){
			String msg = adminManageService.deleteUser(deleteId);
			return ResponseEntity.ok(msg);
	}
	
}
