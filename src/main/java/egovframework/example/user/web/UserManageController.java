package egovframework.example.user.web;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import egovframework.example.naver.dto.GneInfoDto;
import egovframework.example.naver.dto.GneUserDto;
import egovframework.example.naver.dto.NaverTokenDto;
import egovframework.example.naver.dto.NaverUserDto;
import egovframework.example.naver.service.NaverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import egovframework.example.cmmn.service.LoginVO;
import egovframework.example.cmmn.service.ResultVO;
import egovframework.example.cmmn.service.SurveyVO;
import egovframework.example.user.service.IdTokTokVO;
import egovframework.example.user.service.MySelVO;
import egovframework.example.user.service.Students;
import egovframework.example.user.service.Teachers;
import egovframework.example.user.service.UserManageService;
import egovframework.example.user.service.Users;

@RestController
@RequestMapping("/api")
public class UserManageController {
	
	@Resource(name = "userManageService")
	private UserManageService userManageService;

	@Autowired
	@Qualifier(value = "naverServiceImpl")
	private NaverService naverService;
	
	/**
	 * 회원정보 조회
	 */
	@GetMapping("/users")
	ResponseEntity<?> selectUserInfo(@RequestHeader HttpHeaders header) {
		LoginVO loginVO = LoginVO.builder()
				.uniqId(header.get("authorization").get(0))
				.build();

		LoginVO userInfo = userManageService.selectUserInfo(makeLoginVO(header.get("accessToken").get(0)));
		
		Users res = Users.builder()
				.id(userInfo.getId())
				.name(userInfo.getName())
				.userType(userInfo.getUserType())
				.userSpaceInfo(userInfo.getUserSpaceInfo())
				.userSpaceOrgInfo(userInfo.getUserSpaceOrgInfo())
				.uniqId(userInfo.getUniqId())
				.relationInfo(userInfo.getRelationInfo())
				.isFirstInvite(userInfo.getIsFirstInvite())
				.sex(userInfo.getSex())
				.userEmail(userInfo.getUserEmail())
				.brthdy(userInfo.getBrthdy())
				.build();
		
		return ResponseEntity.ok(res);
	}

	/**
	 * 사용자 정보 LoginVO 만들기
	 */
	LoginVO makeLoginVO(String accessToken) {
		NaverTokenDto naverTokenDto = new NaverTokenDto();
		naverTokenDto.setAccess_token(accessToken);

		// 네이버 억세스토큰으로 사용자정보 조회
		NaverUserDto naverUserDto = naverService.procUserInfo(naverTokenDto);

		// 네이버 사용자정보로 경남교육청 사용자정보 조회
		GneInfoDto<GneUserDto> gneUserDto = naverService.procGneUserInfo(naverUserDto);

		LoginVO loginVO = LoginVO.builder()
				.id(naverUserDto.getSid())
				.name(gneUserDto.getData().getUserNm())
				.userEmail(gneUserDto.getData().getUserId())
				.profileImageId(naverUserDto.getThumbnailPhotoUrl())
				.gradeNm(gneUserDto.getData().getStGrade())
				.classNm(gneUserDto.getData().getStClass())
				.userType(naverUserDto.getUserType())
				.relationInfo(gneUserDto.getData().getSchulNm())
				.build();

		return loginVO;
	}
	
	/**
	 * 회원정보 ID 중복체크 함수
	 */
	@GetMapping("/users/checkId")
	ResponseEntity<Boolean> idDuplicatedCheck(@RequestParam(name = "id") String id) {
		return ResponseEntity.ok(userManageService.checkUserById(id));
	}
	
	/**
	 * 회원정보 수정
	 */
	@PutMapping("/users/update")
	ResponseEntity<?> updateUserInfo(
			@RequestHeader HttpHeaders header,
			@RequestBody LoginVO loginVO) 
	{
		LoginVO auth = LoginVO.builder()
				.uniqId(header.get("authorization").get(0))
				.build();
		
		if(!userManageService.authorizationUser(auth)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("유저 인증에 실패했습니다.");
		}
		
		loginVO.setUniqId(auth.getUniqId());
		
		userManageService.updateUserInfoDtl(loginVO);
		
		return ResponseEntity.ok().build();
		
	}
	
	/**
	 * 학생 나의 SEL 알기
	 */
	@GetMapping("/users/mysel")
	ResponseEntity<?> selectMySelList(@RequestHeader(required=false) HttpHeaders header) {
		LoginVO auth = LoginVO.builder()
				.uniqId(header.get("authorization").get(0))
				.build();
		
		if(!userManageService.authorizationUser(auth)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("유저 인증에 실패했습니다.");
		}
		
		if(userManageService.isReallyTeacher(auth.getUniqId())) {
			List<MySelVO> voList = userManageService.selectTeacherSelList(auth);
			HashMap<String, Object> resultMap = new HashMap<String, Object>();
			
			ArrayList<HashMap<String, String>> reportList = new ArrayList<>();
			
			ArrayList<Teachers> infoArr = new ArrayList<>();
			
			
			for(int i=0; i<voList.size(); i++) {
				String[] pollIdList = voList.get(i).getPollIdList().split(",");
				String[] pollNmList = voList.get(i).getPollNmList().split(",");
				String[] isParticipate = voList.get(i).getIsParticipateList().split(",");
				String[] startDate = voList.get(i).getStartDate().split(",");
				String[] endDate = voList.get(i).getEndDate().split(",");
				String[] expired = voList.get(i).getExpiredList().split(",");
				HashMap<String, Integer> map2 = new HashMap<String, Integer>();
				
				for(int j=0; j<pollIdList.length; j++) {
					if(i==0) {
						HashMap<String, String> map = new HashMap<String, String>();
						
						map.put("pollNm", pollNmList[j]);
						map.put("startDate", startDate[j]);
						map.put("endDate", endDate[j]);
						map.put("expired", expired[j]);
						reportList.add(map);
					}
					
					map2.put(pollNmList[j], Integer.parseInt(isParticipate[j]));
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
			}
			
			resultMap.put("reportList", reportList);
			resultMap.put("infoArr", infoArr);
			
			return ResponseEntity.ok(resultMap); 
		} else {
			List<MySelVO> voList = userManageService.selectStudentSelList(auth);
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