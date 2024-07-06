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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import egovframework.example.admin.system.model.CommonCodesDTO;
import egovframework.example.admin.system.model.IpTableDTO;
import egovframework.example.admin.system.model.SiteManageDTO;
import egovframework.example.admin.system.model.SubCommonCodesDTO;
import egovframework.example.admin.system.model.TermsManageDTO;
import egovframework.example.admin.system.service.AdminSystemManageService;
import egovframework.example.cmmn.service.AdminLoginVO;
import egovframework.example.cmmn.service.ResultVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/admin/system")
public class AdminSystemManageController {

	@Resource(name = "adminSystemManageService")
	private AdminSystemManageService adminSystemService;
	
	/**
	 * Insert 및 Update (하위코드제외)
	 * @param data
	 * @param entity
	 * @return
	 */
	@PostMapping("/{entity}/register")
	ResponseEntity<?> insertEntity(@RequestBody Object data, @PathVariable String entity) {
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
	    	   return ResponseEntity.ok(ResultVO.res(HttpStatus.OK,"Unknown entity: " + entity));
       }
    }
	
	@PutMapping("/{entity}/update")
	ResponseEntity<?> updateEntity(@RequestBody Object data, @PathVariable String entity) {
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
	    	   return ResponseEntity.ok(ResultVO.res(HttpStatus.OK,"Unknown entity: " + entity));
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
    ResponseEntity<?> selectEntity(@PathVariable String entity, @PathVariable String id) {
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
	    	   return ResponseEntity.ok(ResultVO.res(HttpStatus.OK,"Unknown entity: " + entity));
       }	
    }
    
	/**
	 * Delete 하위코드제외
	 * @param header
	 * @param entity
	 * @param id
	 * @return
	 */
	@DeleteMapping("/{entity}/{id}")
    ResponseEntity<?> deleteEntity(@PathVariable String entity, @PathVariable String id) {
	    switch (entity.toLowerCase()) {
	       case "site":
	    	   adminSystemService.deleteSite(id);
	    	   break;
	       case "terms":
	    	   adminSystemService.deleteTerms(id);
	    	   break;
	       case "cmmn_code":
	    	   adminSystemService.deleteCommonCode(id);
	    	   break;
	       case "ip":
	    	   adminSystemService.deleteIp(id);
	    	   break;
	       default:
	    	   return ResponseEntity.ok(ResultVO.res(HttpStatus.OK,"Unknown entity: " + entity));
       }
	    return ResponseEntity.ok(ResultVO.res(HttpStatus.OK, entity + "Record ID : " + id + "is delete Successful"));
    }
    
	/**
	 * 전체 select
	 * @param header
	 * @param entity
	 * @param id
	 * @return
	 */
	@GetMapping("/{entity}")
    ResponseEntity<?> selectEntityAll(@PathVariable String entity, @RequestParam(required = false) Map<String,String> search){
	    switch (entity.toLowerCase()) {
	       case "site":
	    	   List<SiteManageDTO> site = adminSystemService.selectSitesAll();
	   		   return ResponseEntity.ok(site);
	       case "terms":
	    	   List<TermsManageDTO> terms = adminSystemService.selectTermsAll();
	    	   return ResponseEntity.ok(terms);
	       case "cmmn_code":
	    	   List<CommonCodesDTO> cmmnCode = adminSystemService.selectCommonCodesByConditions(search);
	    	   return ResponseEntity.ok(cmmnCode);
	       case "ip":
	    	   List<IpTableDTO> ipAddr = adminSystemService.selectIpsAll();
	    	   return ResponseEntity.ok(ipAddr);
	       default:
	    	   return ResponseEntity.ok(ResultVO.res(HttpStatus.OK,"Unknown entity: " + entity));
       }
    }

	/**
	 * 하위 공통 코드 관리
	 */
	@PostMapping("/cmmn_code/{codeId}/sub_code_register")
    ResponseEntity<?> insertSubCommonCode(@PathVariable String codeId, @RequestBody SubCommonCodesDTO subCommonCode) {
		if(adminSystemService.selectCommonCodeById(codeId) == null) {
			return ResponseEntity.ok(ResultVO.res(HttpStatus.OK,"Code Id Not exists"));
		}
		subCommonCode.setCodeId(codeId);
		adminSystemService.insertSubCommonCode(subCommonCode);
    	return ResponseEntity.ok(subCommonCode);
    }
	
	@PutMapping("/cmmn_code/{codeId}/sub_code_update")
	ResponseEntity<?> updateSubCommonCode(@PathVariable String codeId, @RequestBody SubCommonCodesDTO subCommonCode) {
		if(adminSystemService.selectCommonCodeById(codeId) == null) {
			return ResponseEntity.ok(ResultVO.res(HttpStatus.OK,"Code Id Not exists"));
		}
		adminSystemService.updateSubCommonCode(subCommonCode);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/cmmn_code/{codeId}/{subCodeId}")
    ResponseEntity<?> selectSubCommonCodeById(@PathVariable String codeId, @PathVariable String subCodeId) {
		if(adminSystemService.selectCommonCodeById(codeId) == null) {
			return ResponseEntity.ok(ResultVO.res(HttpStatus.OK,"Code Id Not exists"));
		}
		SubCommonCodesDTO subCommonCode = adminSystemService.selectSubCommonCodeById(subCodeId);
    	return ResponseEntity.ok(subCommonCode);
    }
	
	@DeleteMapping("/cmmn_code/{codeId}/{subCodeId}")
    ResponseEntity<?> deleteSubCommonCode(@PathVariable String codeId, @PathVariable String subCodeId) {
		if(adminSystemService.selectCommonCodeById(codeId) == null) {
			return ResponseEntity.ok(ResultVO.res(HttpStatus.OK,"Code Id Not exists"));
		}
    	return ResponseEntity.ok().build();
    }
	
	@GetMapping("/cmmn_code/{codeId}/sub_codes")
    ResponseEntity<?> selectSubCommonCodesByConditions(@PathVariable String codeId, @RequestParam(required = false) Map<String, String> conditions){
		if(adminSystemService.selectCommonCodeById(codeId) == null) {
			return ResponseEntity.ok(ResultVO.res(HttpStatus.OK,"Code Id Not exists"));
		}
		conditions.put("codeId", codeId);
		List<SubCommonCodesDTO> subCommonCodes = adminSystemService.selectSubCommonCodesByConditions(conditions);
    	return ResponseEntity.ok(subCommonCodes);
    }
}
