package egovframework.example.admin.system.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import egovframework.example.admin.system.model.CommonCodesDTO;
import egovframework.example.admin.system.model.IpTableDTO;
import egovframework.example.admin.system.model.SiteManageDTO;
import egovframework.example.admin.system.model.SubCommonCodesDTO;
import egovframework.example.admin.system.model.TermsManageDTO;
import egovframework.example.admin.system.service.AdminSystemManageService;
import egovframework.example.cmmn.service.AdminLoginVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/admin/system")
public class AdminSystemManageController {

	@Resource(name = "adminSystemManageService")
	private AdminSystemManageService adminSystemService;
	
	/**
	 * Insert 및 Update (하위코드제외)
	 * @param header
	 * @param data
	 * @param entity
	 * @return
	 */
	@PostMapping("/{entity}/register")
	ResponseEntity<?> insertSite(@RequestHeader HttpHeaders header,@RequestBody Object data,@PathVariable String entity) {
       switch (entity.toLowerCase()) {
	       case "site":
	    	   adminSystemService.insertSite((SiteManageDTO) data);
	   		   return ResponseEntity.ok().build();
	       case "terms":
	    	   adminSystemService.insertTerms((TermsManageDTO) data);
	    	   return ResponseEntity.ok().build();
	       case "cmmn_code":
	    	   adminSystemService.insertCommonCode((CommonCodesDTO) data);
	    	   return ResponseEntity.ok().build();
	       case "ip":
	    	   adminSystemService.insertIp((IpTableDTO) data);
	    	   return ResponseEntity.ok().build();
	       default:
	           throw new IllegalArgumentException("Unknown entity: " + entity);
       }
    }
	
	@PutMapping("/{entity}/update")
	ResponseEntity<?> updateSite(@RequestHeader HttpHeaders header,@RequestBody Object data,@PathVariable String entity) {
       switch (entity.toLowerCase()) {
	       case "site":
	    	   adminSystemService.updateSite((SiteManageDTO) data);
	   		   return ResponseEntity.ok().build();
	       case "terms":
	    	   adminSystemService.updateTerms((TermsManageDTO) data);
	    	   return ResponseEntity.ok().build();
	       case "cmmn_code":
	    	   adminSystemService.updateCommonCode((CommonCodesDTO) data);
	    	   return ResponseEntity.ok().build();
	       case "ip":
	    	   adminSystemService.updateIp((IpTableDTO) data);
	    	   return ResponseEntity.ok().build();
	       default:
	           throw new IllegalArgumentException("Unknown entity: " + entity);
       }
    }
	/**
	 * SelectEntity (하위코드 제외)
	 * @param header
	 * @param entity
	 * @param id
	 * @return
	 */
	@GetMapping("/{entity}/{id}")
    ResponseEntity<?> selectEntity(@RequestHeader HttpHeaders header,@PathVariable String entity, @PathVariable String id) {
	    switch (entity.toLowerCase()) {
	       case "site":
	    	   log.info("aa");
	    	   SiteManageDTO site = adminSystemService.selectSiteById(id);
	   		   return ResponseEntity.ok(site);
	       case "terms":
	    	   TermsManageDTO terms = adminSystemService.selectTermsById(id);
	    	   return ResponseEntity.ok(terms);
	       case "cmmn_code":
	    	   CommonCodesDTO cmmnCode = adminSystemService.selectCommonCodeById(id);
	    	   return ResponseEntity.ok(cmmnCode);
	       case "ip":
	    	   IpTableDTO ipAddr = adminSystemService.selectIpById(id);
	    	   return ResponseEntity.ok(ipAddr);
	       default:
	           throw new IllegalArgumentException("Unknown entity: " + entity);
       }	
    }
    
	/**
	 * Delete 하위코드제외
	 * @param header
	 * @param entity
	 * @param id
	 * @return
	 */
	@DeleteMapping("/{entity}/delete/{id}")
    ResponseEntity<?> deleteSite(@RequestHeader HttpHeaders header,@PathVariable String entity,@PathVariable String id) {
	    switch (entity.toLowerCase()) {
	       case "site":
	    	   adminSystemService.deleteSite(id);
	   		   return ResponseEntity.ok().build();
	       case "terms":
	    	   adminSystemService.deleteTerms(id);
	   		   return ResponseEntity.ok().build();
	       case "cmmn_code":
	    	   adminSystemService.deleteCommonCode(id);
	   		   return ResponseEntity.ok().build();
	       case "ip":
	    	   adminSystemService.deleteIp(id);
	   		   return ResponseEntity.ok().build();
	       default:
	           throw new IllegalArgumentException("Unknown entity: " + entity);
       }
    }
    
	/**
	 * 전체 select
	 * @param header
	 * @param entity
	 * @param id
	 * @return
	 */
	@GetMapping("/{entity}")
    ResponseEntity<?> selectSitesAll(@RequestHeader HttpHeaders header,@PathVariable String entity,@PathVariable String id){
	    switch (entity.toLowerCase()) {
	       case "site":
	    	   SiteManageDTO site = adminSystemService.selectSiteById(id);
	   		   return ResponseEntity.ok(site);
	       case "terms":
	    	   TermsManageDTO terms = adminSystemService.selectTermsById(id);
	    	   return ResponseEntity.ok(terms);
	       case "cmmn_code":
	    	   CommonCodesDTO cmmnCode = adminSystemService.selectCommonCodeById(id);
	    	   return ResponseEntity.ok(cmmnCode);
	       case "ip":
	    	   IpTableDTO ipAddr = adminSystemService.selectIpById(id);
	    	   return ResponseEntity.ok(ipAddr);
	       default:
	           throw new IllegalArgumentException("Unknown entity: " + entity);
       }
    }

	/*
	 * 하위 공통 코드 관리
	 */
    ResponseEntity<?> insertSubCommonCode(SubCommonCodesDTO subCommonCode) {
    	return ResponseEntity.ok().build();
    }
    ResponseEntity<?> selectSubCommonCodeById(String subCodeId) {
    	return ResponseEntity.ok().build();
    }
    ResponseEntity<?> deleteSubCommonCode(String subCodeId) {
    	return ResponseEntity.ok().build();
    }
    ResponseEntity<?> selectSubCommonCodesByConditions(Map<String, Object> conditions){
    	return ResponseEntity.ok().build();
    }

}
