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
public class AdminIpTableController {

	@Resource(name = "adminSystemManageService")
	private AdminSystemManageService adminSystemService;
	
	@PostMapping("/ip/register")
	ResponseEntity<?> insertEntity(IpTableDTO data) {
		try {
			adminSystemService.insertIp(data);
	        return ResponseEntity.ok(ResultVO.res(HttpStatus.OK,"success"));
		}catch(Exception e){
			return ResponseEntity.ok(ResultVO.res(HttpStatus.OK,"failed"));
		}
    }
	
	@PutMapping("/ip/update")
	ResponseEntity<?> updateEntity(IpTableDTO data) {
		try {
			adminSystemService.updateIp(data);
	        return ResponseEntity.ok(ResultVO.res(HttpStatus.OK,"success"));
		}catch(Exception e){
			return ResponseEntity.ok(ResultVO.res(HttpStatus.OK,"failed"));
		}
    }

	@GetMapping("/ip/{ip}")
    ResponseEntity<?> selectEntity(@PathVariable String ip) {
		try {
			IpTableDTO res = adminSystemService.selectIpById(ip);
	        return ResponseEntity.ok(res);
		}catch(Exception e){
			return ResponseEntity.ok(ResultVO.res(HttpStatus.OK,"failed"));
		}
    }
    
	@DeleteMapping("/ip/{ip}")
    ResponseEntity<?> deleteEntity(@PathVariable String entity, @PathVariable String ip) {
		try {
			adminSystemService.deleteSite(ip);
			return ResponseEntity.ok(ResultVO.res(HttpStatus.OK, "SITE ID : " + ip + "delete Success"));
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
	@GetMapping("/ip")
    ResponseEntity<?> selectEntityAll(IpTableDTO ip){
		try {
			IpTableListDTO ips = adminSystemService.selectIpsAll(ip);
			return ResponseEntity.ok(ips);
		}catch(Exception e){
			return ResponseEntity.ok(ResultVO.res(HttpStatus.OK,"failed"));
		}	    
    }
}
