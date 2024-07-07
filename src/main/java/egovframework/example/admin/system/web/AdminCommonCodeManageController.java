package egovframework.example.admin.system.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import egovframework.example.admin.system.model.CommonCodeListDTO;
import egovframework.example.admin.system.model.CommonCodesDTO;
import egovframework.example.admin.system.model.IpTableDTO;
import egovframework.example.admin.system.model.IpTableListDTO;
import egovframework.example.admin.system.model.SiteListDTO;
import egovframework.example.admin.system.model.SiteManageDTO;
import egovframework.example.admin.system.model.SubCommonCodeListDTO;
import egovframework.example.admin.system.model.SubCommonCodesDTO;
import egovframework.example.admin.system.model.TermsListDTO;
import egovframework.example.admin.system.model.TermsManageDTO;
import egovframework.example.admin.system.service.AdminSystemManageService;
import egovframework.example.cmmn.service.AdminLoginVO;
import egovframework.example.cmmn.service.PaginationVO;
import egovframework.example.cmmn.service.ResultVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/admin/system")
public class AdminCommonCodeManageController {

	@Resource(name = "adminSystemManageService")
	private AdminSystemManageService adminSystemService;
	
	
	@PostMapping("/cmmn_code/register")
	ResponseEntity<?> insertEntity(CommonCodesDTO data) {
		try {
			adminSystemService.insertCommonCode(data);
	        return ResponseEntity.ok(ResultVO.res(HttpStatus.OK,"success"));
		}catch(Exception e){
			return ResponseEntity.ok(ResultVO.res(HttpStatus.OK,"failed"));
		}
    }
	
	@PutMapping("/cmmn_code/update")
	ResponseEntity<?> updateEntity(CommonCodesDTO data) {
		try {
			adminSystemService.updateCommonCode(data);
	        return ResponseEntity.ok(ResultVO.res(HttpStatus.OK,"success"));
		}catch(Exception e){
			return ResponseEntity.ok(ResultVO.res(HttpStatus.OK,"failed"));
		}
    }

	@GetMapping("/cmmn_code/{id}")
    ResponseEntity<?> selectEntity(@PathVariable String id) {
		try {
			CommonCodesDTO res = adminSystemService.selectCommonCodeById(id);
	        return ResponseEntity.ok(res);
		}catch(Exception e){
			return ResponseEntity.ok(ResultVO.res(HttpStatus.OK,"failed"));
		}
    }
    
	@DeleteMapping("/cmmn_code/{id}")
    ResponseEntity<?> deleteEntity(@PathVariable String id) {
		try {
			adminSystemService.deleteCommonCode(id);
			return ResponseEntity.ok(ResultVO.res(HttpStatus.OK, "COMMON CODE ID : " + id + "delete Success"));
		}catch(Exception e){
			return ResponseEntity.ok(ResultVO.res(HttpStatus.OK,"failed"));
		}
	    
    }
	
	@GetMapping("/cmmn_code")
    ResponseEntity<?> selectEntityAll(CommonCodesDTO code){
		try {
			CommonCodeListDTO codes = adminSystemService.selectCommonCodesByConditions(code);
			return ResponseEntity.ok(codes);
		}catch(Exception e){
			return ResponseEntity.ok(ResultVO.res(HttpStatus.OK,"failed"));
		}	    
    }
	
	@GetMapping("/cmmn_code/check")
	ResponseEntity<?> checkSiteDuplicated(@RequestParam String id){
	    boolean res = adminSystemService.checkCommonCodeById(id);
	    return ResponseEntity.ok(res);
	}
}
