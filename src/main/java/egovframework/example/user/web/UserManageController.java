package egovframework.example.user.web;


import egovframework.example.cmmn.service.LoginVO;
import egovframework.example.cmmn.service.SurveyVO;
import egovframework.example.naver.dto.*;
import egovframework.example.naver.service.NaverService;
import egovframework.example.pool.service.PoolManageService;
import egovframework.example.pool.service.PoolManageVO;
import egovframework.example.user.service.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserManageController {
	
	@Resource(name = "userManageService")
	private UserManageService userManageService;

	@Resource(name = "poolManageService")
	private PoolManageService poolManageService;

	@Autowired
	@Qualifier(value = "naverServiceImpl")
	private NaverService naverService;
	
	/**
	 * 회원정보 조회
	 */
	@GetMapping("/users")
	ResponseEntity<?> selectUserInfo(@RequestHeader LoginVO header) {

		return ResponseEntity.ok(userManageService.selectUserInfo(header));
	}
	
	/**
	 * 회원정보 ID 중복체크 함수
	 */
	@GetMapping("/users/checkId")
	ResponseEntity<Boolean> idDuplicatedCheck(@RequestBody LoginVO loginVO) {

		return ResponseEntity.ok(userManageService.checkUserById(loginVO));
	}
	
	/**
	 * 회원정보 수정
	 */
	@PutMapping("/users/update")
	ResponseEntity<?> updateUserInfo(@RequestHeader LoginVO header, @RequestBody LoginVO loginVO) {
		
		if(!userManageService.authorizationUser(header)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("유저 인증에 실패했습니다.");
		}
		
		loginVO.setUniqId(header.getUniqId());
		
		userManageService.updateUserInfoDtl(loginVO);
		
		return ResponseEntity.ok().build();
	}
	
	/**
	 * 학생 나의 SEL 알기
	 */
	@GetMapping("/users/mysel")
	ResponseEntity<?> selectMySelList(@RequestHeader LoginVO header) {
		
		if(userManageService.isReallyTeacher(header)) {
			// 교사일 시
			List<MySelVO> voList = userManageService.selectTeacherSelList(header);
			ArrayList<Teachers> gneList = makeGneList(header);

			HashMap<String, Object> resultMap = new HashMap<String, Object>();
			ArrayList<HashMap<String, String>> reportList = new ArrayList<>();
			ArrayList<Teachers> infoArr = new ArrayList<>();

			for(MySelVO selVO : voList) {
				HashMap<String, Integer> map2 = new HashMap<String, Integer>();
				
				for(int j=0; j<selVO.getPollIdList().length; j++) {
						HashMap<String, String> map = new HashMap<String, String>();
						
						map.put("pollNm", selVO.getPollNmList()[j]);
						map.put("startDate", selVO.getStartDateList()[j]);
						map.put("endDate", selVO.getEndDateList()[j]);
						map.put("expired", selVO.getExpiredList()[j]);
						reportList.add(map);

					map2.put(selVO.getPollNmList()[j], Integer.parseInt(selVO.getIsParticipateList()[j]));
				}
				
				HashMap<String, String> stateFinList = new HashMap<>();
				
				if(voList.get(i).getPollIDForfrstRegisterPnttm() != null && voList.get(i).getFrstRegisterPnttm() != null) {
					String[] pollIDForfrstRegisterPnttm = voList.get(i).getPollIDForfrstRegisterPnttm().split(",");
					String[] frstRegisterPnttm = voList.get(i).getFrstRegisterPnttm().split(",");
					
					for(int k=0; k<pollIDForfrstRegisterPnttm.length; k++) {
						
						stateFinList.put(pollIDForfrstRegisterPnttm[k], frstRegisterPnttm[k]);
					}
				}
				
				Teachers teacher = Teachers.builder()
						.userId(voList.get(i).getUserId())
						.name(voList.get(i).getName())
						.email(voList.get(i).getEmail())
						.classInfo(voList.get(i).getClassInfo())
						.sex(voList.get(i).getSex())
						.stateList(map2)
						.stateFinList(stateFinList)
						.build();

				infoArr.add(teacher);

				ArrayList<Integer> removeIdx = new ArrayList<>();

				for(int l=0; l<gneList.size(); l++) {
					if(StringUtils.isEmpty(gneList.get(l).getName()) || StringUtils.isEmpty(gneList.get(l).getClassInfo())) {
						continue;
					}

					if(gneList.get(l).getName().equals(teacher.getName()) && gneList.get(l).getClassInfo().equals(teacher.getClassInfo())) {
						removeIdx.add(l);
					}
				}

				for(int idx : removeIdx) {
					gneList.remove(idx);
				}
			}

			infoArr.addAll(gneList);

			if(reportList.isEmpty()) {
				List<PoolManageVO> list = poolManageService.selectReportsTeacher();

				for(PoolManageVO vo : list) {
					HashMap<String, String> map = new HashMap<String, String>();

					map.put("pollNm", vo.getPollNm());
					map.put("startDate", vo.getStartDate());
					map.put("endDate", vo.getEndDate());
					map.put("expired", vo.getExpired() ? "1" : "0");
					reportList.add(map);
				}

				resultMap.put("reportList", reportList);
			} else {
				resultMap.put("reportList", reportList);
			}

			resultMap.put("infoArr", infoArr);

			return ResponseEntity.ok(resultMap); 
		} else {
			// 학생일 시
			List<MySelVO> voList = userManageService.selectStudentSelList(header);
			ArrayList<Students> resultList = new ArrayList<Students>();
			
			for(MySelVO vo : voList) {
				Students result = Students.builder()
						.pollNm(vo.getPollNm())
						.pollId(vo.getPollId())
						.startDate(vo.getStartDate())
						.endDate(vo.getEndDate())
						.status(vo.getStatus())
						.expired(vo.getExpired())
						.build();
				
				resultList.add(result);
			}
			
			return ResponseEntity.ok(resultList);
		}

	}

	/**
	 * SEL 알기 - gne 학생 목록 생성 함수
	 */
	public ArrayList<Teachers> makeGneList(LoginVO header) {

		LoginVO loginVO = userManageService.selectUserInfo(header);

		GneUserDto gneUserDto = new GneUserDto();
		gneUserDto.setSchulCode(loginVO.getSchulCode());
		gneUserDto.setStGrade(loginVO.getGradeNm());
		gneUserDto.setStClass(loginVO.getClassCode());

		GneInfoDto<GneUserDto> gneInfoDto = new GneInfoDto<GneUserDto>();
		gneInfoDto.setData(gneUserDto);

		ArrayList<Teachers> infoArr = new ArrayList<>();

		for(GneSchoolUserDto dto : naverService.procGneSchoolUserInfo(gneInfoDto).getData()) {
			HashMap<String, Integer> map2 = new HashMap<String, Integer>();
			map2.put("마음알기 설문1", 0);
			map2.put("마음알기 설문2", 0);
			map2.put("마음알기 설문3", 0);
			map2.put("마음알기 설문4", 0);
			map2.put("마음알기 설문5", 0);
			map2.put("마음알기 설문6", 0);

			Teachers teacher = Teachers.builder()
					.userId("")
					.name(dto.getUserNm())
					.email(dto.getStGrade() + "학년 " + dto.getStClass() + "반 " + dto.getStNumber() + "번")
					.classInfo(dto.getStGrade() + "학년 " + dto.getStClass() + "반")
					.sex("F")
					.stateList(map2)
					.stateFinList(new HashMap<>())
					.build();

			infoArr.add(teacher);
		}

		return infoArr;
	}
	
	/**
	 * 아이디 톡톡 선생님
	 */
	@GetMapping("/users/idTokTok")
	public ResponseEntity<?> selectIdTokTokTeacher(@RequestHeader HttpHeaders header, @RequestParam String id) {
		LoginVO auth = LoginVO.builder()
				.uniqId(header.get("authorization").get(0))
				.build();
		
		if(!userManageService.authorizationUser(auth)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("유저 인증에 실패했습니다.");
		}
		
		LoginVO teacherInfo = userManageService.isReallyTeacherDtl(auth.getUniqId());
		teacherInfo.setUniqId(id);
		
		if(teacherInfo.getIsTeacher()) {
			HashMap<String, Object> result = new HashMap<String, Object>();
			
			for(IdTokTokVO vo : userManageService.selectIdTokTokTeacher(teacherInfo)) {
				HashMap<String, Object> map = new HashMap<String, Object>();
				
				map.put("score", vo.getPoolScore());
				map.put("mean", vo.getPoolMean());
				map.put("stddev", vo.getPoolStddev());
				
				result.put(vo.getQesitmAnswerType(), map);
			}
			
			return ResponseEntity.ok(result);
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	
	@PutMapping("/users/research")
	public ResponseEntity<?> insertResearchResult(@RequestHeader HttpHeaders header, @RequestBody SurveyVO vo) {
		LoginVO auth = LoginVO.builder()
				.uniqId(header.get("authorization").get(0))
				.build();
		
		if(!userManageService.authorizationUser(auth)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("유저 인증에 실패했습니다.");
		}
		
		if(userManageService.isReallyTeacher(auth.getUniqId())) {
			vo.setTeacherId(auth.getUniqId());
			vo.setQesType("idtt");
			
			if(userManageService.selectSurveyHistoryisExist(vo)) {
				userManageService.updateResearchResult(vo);
			} else {
				userManageService.insertResearchResult(vo);
			}
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("선생님 권한이 필요합니다.");
		}
		
		return ResponseEntity.ok().build();
	}
}