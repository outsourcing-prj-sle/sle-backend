package egovframework.example.user.controller;


import egovframework.example.cmmn.service.LoginVO;
import egovframework.example.cmmn.service.SurveyVO;
import egovframework.example.user.dto.TeachersDTO;
import egovframework.example.user.service.*;
import egovframework.example.user.utils.UserManageUtils;
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

	@Resource(name = "userManageUtils")
	private UserManageUtils userManageUtils;

	/**
	 * 회원정보 조회
	 */
	@GetMapping("/users")
	public ResponseEntity<?> selectUserInfo(@RequestHeader String authorization) {
		LoginVO header = LoginVO.builder().authorization(authorization).build();

		return ResponseEntity.ok(userManageService.selectUserInfo(header));
	}

	/**
	 * 회원정보 수정
	 */
	@PutMapping("/users/update")
	public ResponseEntity<?> updateUserInfo(@RequestHeader String authorization, @RequestBody LoginVO loginVO) {
		LoginVO header = LoginVO.builder().authorization(authorization).build();

		if(!userManageService.authorizationUser(header)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("유저 인증에 실패했습니다.");
		}

		loginVO.setAuthorization(header.getAuthorization());

		userManageService.updateUserInfoDtl(loginVO);

		return ResponseEntity.ok().build();
	}

	/**
	 * 학생 나의 SEL 알기
	 */
	@GetMapping("/users/mysel")
	public ResponseEntity<?> selectMySelList(@RequestHeader String authorization) {
		LoginVO header = LoginVO.builder().authorization(authorization).build();

		if(userManageService.isReallyTeacher(header)) {
			// 교사일 시
			List<MySelVO> voList = userManageService.selectTeacherSelList(header);
			ArrayList<TeachersDTO> gneList = userManageUtils.makeGneList(header);

			return ResponseEntity.ok(userManageUtils.makeMySelTeacherResultDTO(voList, gneList));
		} else {
			// 학생일 시
			return ResponseEntity.ok(userManageService.selectStudentSelList(header));
		}
	}

	/**
	 * 아이디 톡톡 선생님
	 */
	@GetMapping("/users/idTokTok")
	public ResponseEntity<?> selectIdTokTokTeacher(@RequestHeader String authorization, @RequestParam String id) {
		LoginVO header = LoginVO.builder().authorization(authorization).build();

		if(!userManageService.authorizationUser(header)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("유저 인증에 실패했습니다.");
		}

		LoginVO teacherInfo = userManageService.isReallyTeacherDtl(header);
		teacherInfo.setAuthorization(id);

		// 교사 권한 확인
		if(teacherInfo.getIsTeacher()) {
			HashMap<String, Object> result = new HashMap<String, Object>();

			for(IdTokTokVO vo : userManageService.selectIdTokTokTeacher(teacherInfo)) {

				result.put(
						vo.getQesitmAnswerType(),
						new HashMap<String, String>() {{
							put("score", vo.getPollScore());
							put("avg", vo.getPollAvg());
							put("stddev", vo.getPollStddev());
						}}
				);
			}

			return ResponseEntity.ok(result);
		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}

	@PutMapping("/users/research")
	public ResponseEntity<?> insertResearchResult(@RequestHeader String authorization, @RequestBody SurveyVO vo) {
		LoginVO header = LoginVO.builder().authorization(authorization).build();

		if(!userManageService.authorizationUser(header)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("유저 인증에 실패했습니다.");
		}

		// 교사 권한 확인
		if(userManageService.isReallyTeacher(header)) {
			vo.setTeacherId(header.getAuthorization());
			vo.setQesType("idtt");

			userManageService.insertResearchResult(vo);

			return ResponseEntity.ok().build();
		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("선생님 권한이 필요합니다.");
	}
}