package egovframework.example.pool.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import egovframework.example.cmmn.service.LoginVO;
import egovframework.example.pool.service.Pool;
import egovframework.example.pool.service.PoolDtl;
import egovframework.example.pool.service.PoolManageService;
import egovframework.example.pool.service.PoolManageVO;
import egovframework.example.user.service.UserManageService;

@RestController
@RequestMapping("/reports")
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
		HashMap<String, List<Pool>> map = new HashMap<>();
		
		if(userManageService.isReallyTeacher(poolManageVO.getUniqId())) {
			List<PoolManageVO> list = poolManageService.selectReportsTeacher(poolManageVO);
			
			List<Pool> progress = new ArrayList<>(); 
			List<Pool> expired = new ArrayList<>();
			
			for(PoolManageVO vo : list) {
				Pool pool = new Pool(vo);
				
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
			
			List<Pool> todos = new ArrayList<>(); 
			List<Pool> dones = new ArrayList<>();
			
			for(PoolManageVO vo : list) {
				Pool pool = new Pool(vo);
				
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
	 * 회원 마음알기 설문 상세 조회
	 * @param pollId
	 * @param poolManageVO
	 */
	@GetMapping("/status/{pollId}")
	public ResponseEntity<?> selectReportsDtl(
			@RequestHeader HttpHeaders header,
			@PathVariable String pollId) 
	{
		PoolManageVO poolManageVO = new PoolManageVO();
		LoginVO auth = LoginVO.builder()
				.uniqId(header.get("authorization").get(0))
				.build();
		
		if(!poolManageService.authorizationUser(auth)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("유저 인증에 실패했습니다.");
		}
		
		poolManageVO.setUniqId(auth.getUniqId());
		poolManageVO.setPollId(pollId);
		 
		return ResponseEntity.ok(selectReportsDtl(poolManageVO));
	}
	
	
	/**
	 * 회원 마음알기 설문 상세 함수
	 * @param poolManageVO
	 * @return
	 */
	public PoolDtl selectReportsDtl(PoolManageVO poolManageVO) {
		if(StringUtils.isEmpty(poolManageVO.getUniqId()) || StringUtils.isEmpty(poolManageVO.getPollId())) {
			return PoolDtl.builder().build();
		}
		
		PoolManageVO vo = poolManageService.selectReportsDtl(poolManageVO);
		 List<PoolManageVO> qnaList = poolManageService.selectReportsQnA(poolManageVO);
		 
		 ArrayList<String> stepList = new ArrayList<String>();
		 
		 ArrayList<HashMap<String, Object>> metaList = new ArrayList<HashMap<String,Object>>();
		 
		 if(vo.getStatus().equals("progress") || vo.getStatus().equals("done")) {
			 String[] randSnList = vo.getQesitmSnList().split(",");
			 String[] snList = vo.getQesitmSn().split(",");
			 
			 for(int i=0; i<randSnList.length; i++) {
				 int sn = Integer.parseInt(randSnList[i]);
				 
				 if(sn > snList.length) {
					 stepList.add(randSnList[i] + ":");
				 } else {
					 stepList.add(snList[sn-1]);
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
		 
		PoolDtl poolDtl = PoolDtl.builder()
				 .status(vo.getStatus())
				 .pollNm(vo.getPollNm())
				 .startDate(vo.getStartDate())
				 .endDate(vo.getEndDate())
				 .step(stepList)
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
		
		if(poolManageService.selectIsDone(poolManageVO)) {
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
		
		if(poolManageService.selectIsDone(poolManageVO)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
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
		
		if(poolManageService.selectIsDone(poolManageVO)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		} else {
			poolManageService.updateReportsStatus(poolManageVO);
		}
		
		return ResponseEntity.ok().build();
	}
}
