package egovframework.example.pool.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import egovframework.example.cmmn.service.LoginVO;
import egovframework.example.pool.dto.PoolDTO;
import egovframework.example.pool.dto.PoolDtlDTO;
import egovframework.example.pool.service.PoolManageService;
import egovframework.example.pool.service.PoolManageVO;
import egovframework.example.pool.dto.PoolNoticeDTO;
import egovframework.example.user.service.UserManageService;

@RestController
@RequestMapping("/api/reports")
public class PoolManageController {
	@Resource(name = "poolManageService")
	private PoolManageService poolManageService;
	
	@Resource(name = "userManageService")
	private UserManageService userManageService;
	
	/**
	 * 회원 마음알기 설문 목록 조회
	 */
	@GetMapping("/status")
	public ResponseEntity<?> selectReports(@RequestHeader HttpHeaders header) {
		PoolManageVO poolManageVO = new PoolManageVO();
		LoginVO auth = LoginVO.builder()
				.uniqId(header.get("authorization").get(0))
				.build();
		
		if(!poolManageService.authorizationUser(auth)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("유저 인증에 실패했습니다.");
		}
		
		poolManageVO.setUniqId(auth.getUniqId());
		HashMap<String, List<PoolDTO>> map = new HashMap<>();
		
		if(userManageService.isReallyTeacher(poolManageVO.getUniqId())) {
			List<PoolManageVO> list = poolManageService.selectReportsTeacher();
			
			List<PoolDTO> progress = new ArrayList<>();
			List<PoolDTO> expired = new ArrayList<>();
			
			for(PoolManageVO vo : list) {
				PoolDTO pool = new PoolDTO(vo);
				
				if(pool.getExpired()) {
					expired.add(pool);
				} else {
					progress.add(pool);
				}
			}
			
			map.put("todo", progress);
			map.put("done", expired);
			
		} else {
			List<PoolManageVO> list = poolManageService.selectReports(poolManageVO);
			
			List<PoolDTO> todos = new ArrayList<>();
			List<PoolDTO> dones = new ArrayList<>();
			
			for(PoolManageVO vo : list) {
				PoolDTO pool = new PoolDTO(vo);
				
				if(pool.getStatus().equals("done")) {
					dones.add(pool);
				} else {
					todos.add(pool);
				}
			}
			
			map.put("todo", todos);
			map.put("done", dones);
		}
		
		return ResponseEntity.ok(map);
	}
	
	/**
	 * 회원 마음알기 설문 상세 함수
	 * @param poolManageVO
	 * @return
	 */
	public PoolDtlDTO selectReportsDtl(PoolManageVO poolManageVO) {
		if(StringUtils.isEmpty(poolManageVO.getUniqId()) || StringUtils.isEmpty(poolManageVO.getPollId())) {
			return PoolDtlDTO.builder().build();
		}
		
		PoolManageVO vo = poolManageService.selectReportsDtl(poolManageVO);
		 List<PoolManageVO> qnaList = poolManageService.selectReportsQnA(poolManageVO);
		 
		 StringBuilder stepList = new StringBuilder();
		 
		 ArrayList<HashMap<String, Object>> metaList = new ArrayList<HashMap<String,Object>>();
		 
		 String[] randSnList = vo.getQesitmSnList().split(",");
		 
		 if(vo.getQesitmSn() == null) {
			 for(int i=0; i<randSnList.length; i++) {
				 int sn = Integer.parseInt(randSnList[i]);
				 
				 stepList.append(sn).append(":/");
				 
				 if(i < randSnList.length-1) {
					 stepList.append(",");
				 }
			 }
		 } else {
			 String[] snList = vo.getQesitmSn().split(",");
			 
			 for(int i=0; i<randSnList.length; i++) {
				 int sn = Integer.parseInt(randSnList[i]);
				 
				 for(int j=0; j<snList.length; j++) {
					 
					 String[] q = snList[j].split(":");
					 
					 if(sn == Integer.parseInt(q[0])) {
						 stepList.append(snList[j]).append(",");
						 break;
					 }
					
					 if(j == snList.length-1) {
						 stepList.append(sn).append(":/,");
					 }
				 }
				 
				 if(i == randSnList.length-1) {
					 stepList.deleteCharAt(stepList.length()-1);
				 }
			 }
		 }
		 
		for(PoolManageVO qna : qnaList) {
			HashMap<String, Object> metaMap = new HashMap<String, Object>();
			ArrayList<String> answerText = new ArrayList<String>();
			ArrayList<String> answerImage = new ArrayList<String>();
			
			metaMap.put("Q", qna.getQesitmSj());
			
			for(String at : qna.getQesitmAnswerText().split(",")) {
				answerText.add(at);
			}
			metaMap.put("AT", answerText);
			
			for(String ai : qna.getQesitmAnswerImage().split(",")) {
				answerImage.add(ai);
			}
			metaMap.put("AI", answerImage);
			
			metaList.add(metaMap);
		}
		 
		PoolDtlDTO poolDtl = PoolDtlDTO.builder()
				 .status(vo.getStatus())
				 .pollNm(vo.getPollNm())
				 .startDate(vo.getStartDate())
				 .endDate(vo.getEndDate())
				 .step(stepList.toString())
				 .metadata(metaList)
				 .isSave(vo.getIsSave())
				 .isVoice(vo.getIsVoice())
				 .build();
		
		return poolDtl;
	}

	
	/**
	 * 회원 마음알기 설문 저장
	 * @param pollId
	 * @param poolManageVO
	 */
	@PutMapping("/save")
	public ResponseEntity<?> insertReports(
			@RequestHeader HttpHeaders header,
			@RequestBody PoolManageVO poolManageVO) 
	{
		LoginVO auth = LoginVO.builder()
				.uniqId(header.get("authorization").get(0))
				.build();
		
		if(!poolManageService.authorizationUser(auth)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("유저 인증에 실패했습니다.");
		}
		
		poolManageVO.setUniqId(auth.getUniqId());
		
		if(poolManageService.selectIsDone(poolManageVO) == 1) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		} else {
			poolManageService.insertReports(poolManageVO);
		}
		
		return ResponseEntity.ok(selectReportsDtl(poolManageVO));
	}
	
	
	/**
	 * 회원 마음알기 설문 시작
	 * @param poolManageVO
	 */
	@PutMapping("/start")
	public ResponseEntity<?> insertReportsStatus(
			@RequestHeader HttpHeaders header,
			@RequestBody PoolManageVO poolManageVO) {
		LoginVO auth = LoginVO.builder()
				.uniqId(header.get("authorization").get(0))
				.build();
		
		if(!poolManageService.authorizationUser(auth)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("유저 인증에 실패했습니다.");
		}
		poolManageVO.setUniqId(auth.getUniqId());
		
		if(poolManageService.selectIsDone(poolManageVO) == 1) {
			return ResponseEntity.ok(selectReportsDtl(poolManageVO));
		} else {
			poolManageService.insertReportsStatus(poolManageVO);
		}
		
		return ResponseEntity.ok(selectReportsDtl(poolManageVO));
	}
	
	/**
	 * 회원 마음알기 설문 완료
	 * @param poolManageVO
	 */
	@PutMapping("/complete")
	public ResponseEntity<?> updateReportsStatus(
			@RequestHeader HttpHeaders header,
			@RequestBody PoolManageVO poolManageVO) {
		LoginVO auth = LoginVO.builder()
				.uniqId(header.get("authorization").get(0))
				.build();
		
		if(!poolManageService.authorizationUser(auth)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("유저 인증에 실패했습니다.");
		}
		poolManageVO.setUniqId(auth.getUniqId());
		
		if(poolManageService.selectIsDone(poolManageVO) == 1) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		} else {
			poolManageService.insertReports(poolManageVO);
			poolManageService.updateReportsStatus(poolManageVO);
		}
		
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/notice")
	public ResponseEntity<?> selectReportsNotice(@RequestHeader HttpHeaders header,	@RequestParam String pollId){
		LoginVO auth = LoginVO.builder()
				.uniqId(header.get("authorization").get(0))
				.build();
		
		if(!poolManageService.authorizationUser(auth)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("유저 인증에 실패했습니다.");
		}
		
		PoolNoticeDTO poolNoticeDTO = poolManageService.selectReportsNotice(pollId);
		poolNoticeDTO.setUniqId(auth.getUniqId());
		
		return ResponseEntity.ok(poolNoticeDTO);
	}
}
