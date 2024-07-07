package egovframework.example.user.controller;


import egovframework.example.cmmn.CustomException;
import egovframework.example.cmmn.service.LoginVO;
import egovframework.example.cmmn.service.SurveyVO;
import egovframework.example.user.dto.*;
import egovframework.example.user.service.*;
import egovframework.example.user.utils.UserManageUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class UserManageController {

	@Resource(name = "userManageService")
	private UserManageService userManageService;

	@Resource(name = "userManageUtils")
	private UserManageUtils userManageUtils;
    @Autowired
    private egovframework.example.poll.utils.pollManageUtils pollManageUtils;

	/**
	 * 회원정보 조회
	 */
	@GetMapping("/users")
	public ResponseEntity<?> selectUserInfo(@RequestHeader HashMap<String, String> req) {
		LoginVO header = LoginVO.builder().authorization(req.get("authorization")).build();

		if(!userManageService.authorizationUser(header)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("유저 인증에 실패했습니다.");
		}

		return ResponseEntity.ok(userManageService.selectUserInfo(header));
	}

	/**
	 * 회원정보 수정
	 */
	@PutMapping("/users/update")
	public ResponseEntity<?> updateUserInfo(@RequestHeader HashMap<String, String> req, @RequestBody LoginVO loginVO) {
		LoginVO header = LoginVO.builder().authorization(req.get("authorization")).build();

		if(!userManageService.authorizationUser(header)) {
			throw new CustomException("유저 인증에 실패했습니다.");
		} else if(StringUtils.isEmpty(loginVO.getSex())){
			throw new CustomException("sex는 필수값입니다.");
		} else if(StringUtils.isEmpty(loginVO.getBrthdy())){
			throw new CustomException("brthdy는 필수값입니다.");
		}

		loginVO.setAuthorization(header.getAuthorization());

		userManageService.updateUserInfoDtl(loginVO);

		return ResponseEntity.ok().build();
	}

	/**
	 * 학생 나의 SEL 알기
	 */
	@GetMapping("/users/mysel")
	public ResponseEntity<?> selectMySelList(@RequestHeader HashMap<String, String> req) {
		LoginVO header = LoginVO.builder().authorization(req.get("authorization")).build();

		if(!userManageService.authorizationUser(header)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("유저 인증에 실패했습니다.");
		}

		if(userManageService.isReallyTeacher(header)) {
			// 교사일 시
			List<MySelVO> voList = userManageService.selectTeacherSelList(header);
			ArrayList<TeachersDTO> gneList = userManageUtils.makeGneList(header);

			return ResponseEntity.ok(userManageUtils.makeMySelTeacherResultDTO(voList, gneList));
		} else {
			// 학생일 시
			List<StudentsDTO> dtoList = userManageService.selectStudentSelList(header);

			return ResponseEntity.ok(
					dtoList.stream()
							.filter(dto -> pollManageUtils.isAllowedPoll(header.getAuthorization(), dto.getPollId()))
							.collect(Collectors.toList())
			);
		}
	}

	/**
	 * 아이디 톡톡 선생님
	 */
	@GetMapping("/users/idTokTok")
	public ResponseEntity<?> selectIdTokTokTeacher(@RequestHeader HashMap<String, String> req, @RequestParam String id) {
		LoginVO header = LoginVO.builder().authorization(req.get("authorization")).build();

		if(!userManageService.authorizationUser(header)) {
			throw new CustomException("유저 인증에 실패했습니다.");
		} else if(StringUtils.isEmpty(id)) {
			throw new CustomException("id는 필수값입니다.");
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
	public ResponseEntity<?> insertResearchResult(@RequestHeader HashMap<String, String> req, @RequestBody SurveyVO vo) {
		LoginVO header = LoginVO.builder().authorization(req.get("authorization")).build();

		if(!userManageService.authorizationUser(header)) {
			throw new CustomException("유저 인증에 실패했습니다.");
		} else if(StringUtils.isEmpty(vo.getId())) {
			throw new CustomException("id는 필수값입니다.");
		} else if(StringUtils.isEmpty(vo.getQesAnswer())) {
			throw new CustomException("qesAnswer는 필수값입니다.");
		}

		// 교사 권한 확인
		if(userManageService.isReallyTeacher(header)) {
			vo.setTeacherId(header.getAuthorization());

			userManageService.insertResearchResult(vo);

			return ResponseEntity.ok().build();
		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("선생님 권한이 필요합니다.");
	}

	@GetMapping("/users/idttLT")
	public ResponseEntity<?> selectIdttLearningTendency(@RequestHeader HashMap<String, String> req, @RequestParam String id) {
		LoginVO header = LoginVO.builder().authorization(req.get("authorization")).build();

		if(!userManageService.authorizationUser(header)) {
			throw new CustomException("유저 인증에 실패했습니다.");
		}

		if(StringUtils.isEmpty(id)) {
			throw new CustomException("id는 필수값입니다.");
		}

//		return ResponseEntity.ok(userManageService.selectIdttLT(header, id));
		return ResponseEntity.ok(makeDummies());
	}

	IdttLTResultDTO<ClassPersonalityDTO> makeDummies() {
		IdttLTResultDTO<ClassPersonalityDTO> result = new IdttLTResultDTO<ClassPersonalityDTO>();
		ArrayList<HashMap<String, String>> userPersonality = new ArrayList<HashMap<String, String>>() {{
			add(new HashMap<String, String>() {{
				put("자주보고 점검하기", "20");
				put("건너뛰며 점검하기", "80");
				put("소통하며 학습하기", "0");
				put("독립적으로 학습하기", "100");
				put("신속하게 과제하기", "0");
				put("느긋하게 과제하기", "100");
			}});
		}};

		ArrayList<ClassPersonalityDTO> classPersonality = new ArrayList<ClassPersonalityDTO>() {{
			add(new ClassPersonalityDTO(){{
				setType1(new ClassPersonalityDtlDTO() {{
					setValues(new ArrayList<Integer>() {{
						add(3);
						add(12);
					}});
					setLabels(new ArrayList<String>() {{
						add("자주보고 점검하기");
						add("건너뛰며 점검하기");
					}});
				}});
				setType2(new ClassPersonalityDtlDTO() {{
					setValues(new ArrayList<Integer>() {{
						add(2);
						add(13);
					}});
					setLabels(new ArrayList<String>() {{
						add("신속하게 과제하기");
						add("느긋하게 과제하기");
					}});
				}});
				setType3(new ClassPersonalityDtlDTO() {{
					setValues(new ArrayList<Integer>() {{
						add(8);
						add(7);
					}});
					setLabels(new ArrayList<String>() {{
						add("소통하며 학습하기");
						add("독립적으로 학습하기");
					}});
				}});
			}});
		}};

		result.setUserPersonality(userPersonality);
		result.setClassPersonality(classPersonality);

		return result;
	}
}