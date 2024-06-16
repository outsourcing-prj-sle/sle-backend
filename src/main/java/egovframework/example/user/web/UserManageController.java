package egovframework.example.user.web;


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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import egovframework.example.cmmn.service.LoginVO;
import egovframework.example.cmmn.service.ResultVO;
import egovframework.example.user.service.MySelVO;
import egovframework.example.user.service.Students;
import egovframework.example.user.service.Teachers;
import egovframework.example.user.service.UserManageService;
import egovframework.example.user.service.Users;

@RestController
public class UserManageController {
	
	@Resource(name = "userManageService")
	private UserManageService userManageService;
	
	/**
	 * 회원정보 조회
	 */
	@GetMapping("/users")
	ResponseEntity<?> selectUserInfo() {
		LoginVO loginVO = new LoginVO();
		loginVO.setUniqId("USRCNFRM_00000000004");
		
		LoginVO userInfo = userManageService.selectUserInfo(loginVO);
		
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
				.build();
		
		return ResponseEntity.ok(res);
	}
	
	/**
	 * 회원정보 등록
	 */
	@PutMapping("/users/insert")
	ResponseEntity<?> insertUserInfo(@RequestBody LoginVO loginVO) {
		try {
		userManageService.insertUserInfo(loginVO);
		}catch(Exception e) {
			return ResponseEntity.ok(ResultVO.res(HttpStatus.OK,"Register Failed"));
		}
		
		return ResponseEntity.ok(ResultVO.res(HttpStatus.OK,HttpStatus.OK.toString(),loginVO));
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
				.userRole(header.get("role").get(0))
				.userSpaceInfo(header.get("spaceInfo").get(0))
				.gradeNm(header.get("grade").get(0))
				.classNm(header.get("class").get(0))
				.build();
		/*
		String uniqId = header.get("authorization").get(0);
		String userRole = header.get("role").get(0);
		String userSpaceInfo = header.get("spaceInfo").get(0);
		String gradeNm = header.get("grade").get(0);
		String classNm = header.get("class").get(0);
		*/
		if(!userManageService.authorizationUser(auth)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("유저 인증에 실패했습니다.");
		}
		userManageService.updateUserInfo(loginVO);
		return ResponseEntity.ok().build();
		
	}
	
	/**
	 * 학생 나의 SEL 알기
	 */
	@GetMapping("/users/mysel")
	ResponseEntity<?> selectMySelList(@RequestHeader HttpHeaders header, @RequestBody LoginVO loginVO) {
		LoginVO auth = LoginVO.builder()
				.uniqId(header.get("authorization").get(0))
				.userRole(header.get("role").get(0))
				.userSpaceInfo(header.get("spaceInfo").get(0))
				.gradeNm(header.get("grade").get(0))
				.classNm(header.get("class").get(0))
				.build();
		if(!userManageService.authorizationUser(auth)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("유저 인증에 실패했습니다.");
		}
		
		if(StringUtils.isEmpty(auth.getUniqId()) || StringUtils.isEmpty(auth.getUserRole())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("기본 정보가 없습니다.");
		}
		
		loginVO.setUniqId(auth.getUniqId());
		loginVO.setUserRole(auth.getUserRole());
		
		if(loginVO.getUserRole().equals("ROLE_STUDENT")) {
			List<MySelVO> voList = userManageService.selectStudentSelList(loginVO);
			ArrayList<Students> resultList = new ArrayList<Students>();
			
			for(MySelVO vo : voList) {
				Students result = Students.builder()
						.pollNm(vo.getPollNm())
						.startDate(vo.getStartDate())
						.endDate(vo.getEndDate())
						.status(vo.getStatus())
						.expired(vo.getExpired())
						.build();
				
				resultList.add(result);
			}
			
			return ResponseEntity.ok(resultList);
		}
		
		if(loginVO.getUserRole().equals("ROLE_TEACHER")) {
			
//			if(!userManageService.isReallyTeacher(uniqId)) {
//				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("선생님이 아닙니다.");
//			}
			
			if(StringUtils.isEmpty(auth.getUserSpaceInfo()) || StringUtils.isEmpty(auth.getGradeNm()) || StringUtils.isEmpty(auth.getClassNm())) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("선생님 정보가 없습니다.");
			}
			
			loginVO.setUserSpaceInfo(auth.getUserSpaceInfo());
			loginVO.changeUserSpace();
			loginVO.setGradeNm(auth.getGradeNm());
			loginVO.setClassNm(auth.getClassNm());
			
			List<MySelVO> voList = userManageService.selectTeacherSelList(loginVO);
			HashMap<String, Object> resultMap = new HashMap<String, Object>();
			
			ArrayList<HashMap<String, String>> reportList = new ArrayList<>();
			HashMap<String, String> map = new HashMap<String, String>();
			
			ArrayList<Teachers> infoArr = new ArrayList<>();
			HashMap<String, Boolean> map2 = new HashMap<String, Boolean>();
			
			for(int i=0; i<voList.size(); i++) {
				String[] pollIdList = voList.get(i).getPollIdList().split(",");
				String[] pollNmList = voList.get(i).getPollNmList().split(",");
				String[] isParticipate = voList.get(i).getIsParticipateList().split(",");
				
				for(int j=0; j<pollIdList.length; j++) {
					if(i==0) {
						map.put(pollIdList[j], pollNmList[j]);
						reportList.add(map);
					}
					
					map2.put(pollIdList[j], Boolean.parseBoolean(isParticipate[j]));
				}
				
				Teachers teacher = Teachers.builder()
						.userId(voList.get(i).getUserId())
						.name(voList.get(i).getName())
						.email(voList.get(i).getEmail())
						.classInfo(voList.get(i).getClassInfo())
						.sex(voList.get(i).getSex())
						.stateList(map2)
						.build();
				
				infoArr.add(teacher);
			}
			
			resultMap.put("reportList", reportList);
			resultMap.put("infoArr", infoArr);
			
			return ResponseEntity.ok(resultMap); 
		}
		
		return ResponseEntity.notFound().build();
	}
	
}