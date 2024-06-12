package egovframework.example.pool.web;

import javax.annotation.Resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import egovframework.example.pool.service.PoolManageService;
import egovframework.example.pool.service.PoolManageVO;

@RestController
@RequestMapping("/reports")
public class PoolManageController {
	@Resource(name = "poolManageService")
	private PoolManageService poolManageService;
	
	/**
	 * 회원 마음알기 설문 목록 조회
	 */
	@GetMapping("/status")
	public ResponseEntity<?> selectReports() {
		PoolManageVO poolManageVO = new PoolManageVO();
		poolManageVO.setUniqId("USRCNFRM_00000000004");
		
		poolManageService.selectReports(poolManageVO);
		
		return ResponseEntity.ok(poolManageService.selectReports(poolManageVO));
	}
	
	/**
	 * 회원 마음알기 설문 상세 조회
	 * @param pollId
	 * @param poolManageVO
	 */
	@GetMapping("/status/{pollId}")
	public ResponseEntity<?> selectReportsDtl(@PathVariable String pollId) {
		 PoolManageVO poolManageVO = new PoolManageVO();
		 poolManageVO.setUniqId("USRCNFRM_00000000004");
		 poolManageVO.setPollId(pollId);
		 
		 poolManageService.selectReportsDtl(poolManageVO);
		 
		return ResponseEntity.ok(poolManageService.selectReportsDtl(poolManageVO));
	}

	
	/**
	 * 마음알기 설문 문제 및 선지 조회
	 * @param pollId
	 * @param poolManageVO
	 */
	@GetMapping("/questions/{pollId}")
	public ResponseEntity<?> selectReportsQnA(@PathVariable String pollId) {
		PoolManageVO poolManageVO = new PoolManageVO();
	 	poolManageVO.setPollId(pollId);
		
		return ResponseEntity.ok(null);
	}
	
	/**
	 * 회원 마음알기 설문 저장
	 * @param pollId
	 * @param poolManageVO
	 */
	@PutMapping("/save")
	public ResponseEntity<?> insertReports(@RequestBody PoolManageVO poolManageVO) {
		poolManageVO.setUniqId("USRCNFRM_00000000004");
		
		poolManageService.insertReports(poolManageVO);
		
		return ResponseEntity.ok().build();
	}
	
	
	/**
	 * 회원 마음알기 설문 시작 
	 * @param poolManageVO
	 */
	@PutMapping("/start")
	public ResponseEntity<?> insertReportsStatus(@RequestBody PoolManageVO poolManageVO) {
		poolManageVO.setUniqId("USRCNFRM_00000000003");
		
		poolManageService.insertReportsStatus(poolManageVO);
		
		return ResponseEntity.ok(null);
	}
	
	/**
	 * 회원 마음알기 설문 완료
	 * @param poolManageVO
	 */
	@PutMapping("/complete")
	public ResponseEntity<?> updateReportsStatus(@RequestBody PoolManageVO poolManageVO) {
		poolManageVO.setUniqId("USRCNFRM_00000000004");
		
		poolManageService.updateReportsStatus(poolManageVO);
		
		return ResponseEntity.ok().build();
	}
}
