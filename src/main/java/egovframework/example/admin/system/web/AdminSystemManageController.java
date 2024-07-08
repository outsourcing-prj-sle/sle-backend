package egovframework.example.admin.system.web;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import egovframework.example.admin.system.model.SiteListDTO;
import egovframework.example.admin.system.model.SiteManageDTO;
import egovframework.example.admin.system.service.AdminSystemManageService;
import egovframework.example.cmmn.service.ResultVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/admin/system")
public class AdminSystemManageController {

	@Resource(name = "adminSystemManageService")
	private AdminSystemManageService adminSystemService;
	
	@PostMapping("/site/register")
	ResponseEntity<?> insertEntity(SiteManageDTO data,
            @RequestParam(name = "topImage", required = false) MultipartFile topLogoImage,
            @RequestParam(name = "bottomImage", required = false) MultipartFile bottomLogoImage,
            @RequestParam(name = "mainImage", required = false) MultipartFile mainLogoImage) {
		try {
			log.info("여기까지");
			adminSystemService.saveSiteManage(data, topLogoImage, bottomLogoImage, mainLogoImage);
	        return ResponseEntity.ok(ResultVO.res(HttpStatus.OK,"success"));
		}catch(Exception e){
			return ResponseEntity.ok(ResultVO.res(HttpStatus.OK,"failed"));
		}
    }
	
	@PutMapping("/site/update")
	ResponseEntity<?> updateEntity(SiteManageDTO data,
			@RequestParam(name = "topImage", required = false) MultipartFile topLogoImage,
            @RequestParam(name = "bottomImage", required = false) MultipartFile bottomLogoImage,
            @RequestParam(name = "mainImage", required = false) MultipartFile mainLogoImage) {
		try {
			adminSystemService.updateSiteManage(data, topLogoImage, bottomLogoImage, mainLogoImage);
			return ResponseEntity.ok(ResultVO.res(HttpStatus.OK,"success"));
		}catch(Exception e){
			return ResponseEntity.ok(ResultVO.res(HttpStatus.OK,"failed"));
		}
    }

	@GetMapping("/site/{id}")
    ResponseEntity<?> selectEntity(@PathVariable String id) {
		try {
			SiteManageDTO res = adminSystemService.selectSiteById(id);
	        return ResponseEntity.ok(res);
		}catch(Exception e){
			return ResponseEntity.ok(ResultVO.res(HttpStatus.OK,"failed"));
		}
    }
    
	@DeleteMapping("/site/{id}")
    ResponseEntity<?> deleteEntity(@PathVariable String id) {
		try {
			adminSystemService.deleteSite(id);
			return ResponseEntity.ok(ResultVO.res(HttpStatus.OK, "SITE ID : " + id + "delete Success"));
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
	@GetMapping("/site")
    ResponseEntity<?> selectEntityAll(SiteManageDTO site){
		try {
			SiteListDTO sites = adminSystemService.selectSitesAll(site);
			return ResponseEntity.ok(sites);
		}catch(Exception e){
			return ResponseEntity.ok(ResultVO.res(HttpStatus.OK,"failed"));
		}	    
    }

	@GetMapping("/site/check")
	ResponseEntity<?> checkSiteDuplicated(@RequestParam String url){
	    boolean res = adminSystemService.checkSiteByUrl(url);
	    return ResponseEntity.ok(res);
	}
}
