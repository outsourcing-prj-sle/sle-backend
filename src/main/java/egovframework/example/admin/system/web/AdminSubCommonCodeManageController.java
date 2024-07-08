package egovframework.example.admin.system.web;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.RestController;

import egovframework.example.admin.system.model.SubCommonCodeListDTO;
import egovframework.example.admin.system.model.SubCommonCodesDTO;

import egovframework.example.admin.system.service.AdminSystemManageService;

import egovframework.example.cmmn.service.ResultVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/admin/system")
public class AdminSubCommonCodeManageController {

	@Resource(name = "adminSystemManageService")
	private AdminSystemManageService adminSystemService;
	
	/**
	 * 하위 공통 코드 관리
	 */
	@PostMapping("/cmmn_code/{codeId}/sub_code_register")
    ResponseEntity<?> insertSubCommonCode(@PathVariable String codeId,@RequestBody SubCommonCodesDTO subCommonCode) {
		if(adminSystemService.selectCommonCodeById(codeId) == null) {
			return ResponseEntity.ok(ResultVO.res(HttpStatus.OK,"Code Id Not exists"));
		}
		subCommonCode.setCodeId(codeId);
		adminSystemService.insertSubCommonCode(subCommonCode);
    	return ResponseEntity.ok("Success");
    }
	
	@PutMapping("/cmmn_code/{codeId}/sub_code_update")
	ResponseEntity<?> updateSubCommonCode(@PathVariable String codeId,@RequestBody SubCommonCodesDTO subCommonCode) {
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
		adminSystemService.deleteSubCommonCode(subCodeId);
    	return ResponseEntity.ok().build();
    }
	
	@GetMapping("/cmmn_code/{codeId}/sub_codes")
    ResponseEntity<?> selectSubCommonCodesByConditions(@PathVariable String codeId, SubCommonCodesDTO data){
		if(adminSystemService.selectCommonCodeById(codeId) == null) {
			return ResponseEntity.ok(ResultVO.res(HttpStatus.OK,"Code Id Not exists"));
		}
		data.setCodeId(codeId);
		SubCommonCodeListDTO subCommonCodes = adminSystemService.selectSubCommonCodesByConditions(data);
    	return ResponseEntity.ok(subCommonCodes);
    }
	/*
	@PutMapping("/cmmn_code/{codeId}/sub_codes")
	ResponseEntity<?> changeOrder(@PathVariable String codeId, @RequestBody SubCommonCodesDTO subCommonCode){
		SubCommonCodesDTO order = SubCommonCodesDTO.builder()
								.orderNm(subCommonCode.getOrderNm())
								.build();
		return ResponseEntity.ok("failed");
	}
	*/
	/**
	 * 중복 확인
	 * @param id
	 * @return
	 */
	@GetMapping("/sub_code/check")
	ResponseEntity<?> checkCommonCodeDuplicated(@RequestParam String id){
	    boolean code = adminSystemService.checkSubCommonCodeById(id);
	    return ResponseEntity.ok(code);
	}
}
