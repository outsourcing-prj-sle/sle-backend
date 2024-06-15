package egovframework.example.user.web;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import egovframework.example.cmmn.service.LoginVO;
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
		loginVO.setUniqId("USRCNFRM_00000000004");
		
		userManageService.insertUserInfo(loginVO);
		
		return ResponseEntity.ok().build();
	}
	
	/**
	 * 회원정보 수정
	 */
	@PutMapping("/users/update")
	ResponseEntity<?> updateUserInfo(@RequestBody LoginVO loginVO) {
		loginVO.setUniqId("USRCNFRM_00000000004");
		
		userManageService.updateUserInfo(loginVO);
		
		return ResponseEntity.ok().build();
	}
	
	/**
	 * 학생 나의 SEL 알기
	 */
	@GetMapping("/users/mysel")
	ResponseEntity<?> selectMySelList(@RequestBody LoginVO loginVO) {
		loginVO.setUniqId("USRCNFRM_00000000004");
		loginVO.setUserRole("ROLE_STUDENT");
		
//		loginVO.setUserRole("ROLE_TEACHER");
//		loginVO.setUserSpaceInfo("이작초등학교");
//		loginVO.setGradeNm("1");
//		loginVO.setClassNm("3");
		
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