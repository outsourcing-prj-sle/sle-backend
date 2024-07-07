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
public class AdminTermsManageController {

	@Resource(name = "adminSystemManageService")
	private AdminSystemManageService adminSystemService;
	
	
	@PostMapping("/terms/register")
	ResponseEntity<?> insertEntity(TermsManageDTO data) {
		try {
			String res = adminSystemService.insertTerms(data);
	        return ResponseEntity.ok(res);
		}catch(Exception e){
			return ResponseEntity.ok(ResultVO.res(HttpStatus.OK,"failed"));
		}
    }
	
	@PutMapping("/terms/update")
	ResponseEntity<?> updateEntity(TermsManageDTO data) {
		try {
			String res = adminSystemService.updateTerms(data);
	        return ResponseEntity.ok(res);
		}catch(Exception e){
			return ResponseEntity.ok(ResultVO.res(HttpStatus.OK,"failed"));
		}
    }

	@GetMapping("/terms/{id}")
    ResponseEntity<?> selectEntity(@PathVariable String id) {
		try {
			TermsManageDTO res = adminSystemService.selectTermsById(id);
	        return ResponseEntity.ok(res);
		}catch(Exception e){
			return ResponseEntity.ok(ResultVO.res(HttpStatus.OK,"failed"));
		}
    }
    
	@DeleteMapping("/terms/{id}")
    ResponseEntity<?> deleteEntity(@PathVariable String id) {
		try {
			adminSystemService.deleteTerms(id);
			return ResponseEntity.ok(ResultVO.res(HttpStatus.OK, "TERMS ID : " + id + "delete Success"));
		}catch(Exception e){
			return ResponseEntity.ok(ResultVO.res(HttpStatus.OK,"failed"));
		}
	    
    }
    
	/**
	 * 전체 select
	 * @param header
	 * @param entity
	 * @param id
	 * @return
	 */
	@GetMapping("/terms")
    ResponseEntity<?> selectEntityAll(TermsManageDTO terms){
		List<TermsManageDTO> term = adminSystemService.selectTermsAll(terms);
		return ResponseEntity.ok(term);
    }
}
