package egovframework.example.poll.service.impl;

import egovframework.example.cmmn.service.LoginVO;
import egovframework.example.poll.dto.*;
import egovframework.example.poll.service.PollManageService;
import egovframework.example.poll.service.PollManageVO;
import egovframework.example.user.service.UserManageService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service("pollManageService")
public class PollManageServiceImpl implements PollManageService {

	@Resource(name = "pollManageMapper")
	private PollManageMapper mapper;

	@Resource(name = "userManageService")
	private UserManageService userManageService;
	/**
	 * 회원 마음알기 설문 목록 조회
	 * @param pollManageVO
	 * @return
	 */
	@Override
	public List<PollDTO> selectReports(PollManageVO pollManageVO) {
		return mapper.selectReports(pollManageVO);
	}

	/**
	 * 회원 마음알기 설문 선생님 목록 조회
	 * @return
	 */
	@Override
	public List<PollDTO> selectReportsTeacher() {
		return mapper.selectReportsTeacher();
	}

	/**
	 * 회원 마음알기 설문 상세 조회
	 * @param pollManageVO
	 * @return
	 */
	@Override
	public PollManageVO selectReportsDtl(PollManageVO pollManageVO) {
		return mapper.selectReportsDtl(pollManageVO);
	}

	/**
	 * 회원 마음알기 설문 문항 및 선지 조회
	 * @param pollManageVO
	 * @return
	 */
	@Override
	public List<PollManageVO> selectReportsQnA(PollManageVO pollManageVO) {
		return mapper.selectReportsQnA(pollManageVO);
	}

	/**
	 * 회원 마음알기 설문 등록
	 * @param pollManageVO
	 */
	@Override
	public void insertReports(PollManageVO pollManageVO) {
		insertReportsStatus(pollManageVO);

		PollManageVO vo = mapper.selectReportsUserAnswer(pollManageVO);

		if(StringUtils.isEmpty(vo.getQesitmAnswer())) {
			vo.setQesitmAnswer(pollManageVO.getQesitmAnswer());
		} else {
			vo.setQesitmAnswer(vo.getQesitmAnswer().concat(",").concat(pollManageVO.getQesitmAnswer()));
		}

		if(StringUtils.isEmpty(vo.getQesitmAnswerImage())) {
			vo.setQesitmAnswerImage(pollManageVO.getQesitmAnswerImage());
		} else {
			vo.setQesitmAnswerImage(vo.getQesitmAnswerImage().concat(",").concat(pollManageVO.getQesitmAnswerImage()));
		}

		mapper.insertReports(vo);
		updateGneUserInfo(pollManageVO);
	}

	/**
	 * 회원 마음알기 사용자 정보 등록
	 * @param pollManageVO
	 */
	@Override
	public void insertReportsUserInfo(PollManageVO pollManageVO) {
		mapper.insertReportsUserInfo(pollManageVO);
	}

	/**
	 * 회원 마음알기 설문 상태 등록(PROGRESS)
	 * @param pollManageVO
	 */
	@Override
	public void insertReportsStatus(PollManageVO pollManageVO) {
		if(mapper.selectReportsMngIsExist(pollManageVO) == 0) {
			int size = mapper.selectReportsCount(pollManageVO);

			pollManageVO.setQesitmSn(makeRandomString(size));

			mapper.insertReportsStatus(pollManageVO);
			mapper.insertReportsUserInfo(pollManageVO);
		}
	}

	/**
	 * 회원 마음알기 설문 상태 변경(DONE)
	 * @param pollManageVO
	 */
	@Override
	public void updateReportsStatus(PollManageVO pollManageVO) {
		insertReports(pollManageVO);
		mapper.updateReportsStatus(pollManageVO);
		insertReportsScore(pollManageVO);
	}

	/**
	 * 회원 마음알기 점수 등록 함수
	 * @param vo
	 */
	public void insertReportsScore(PollManageVO vo) {
		PollManageVO pollManageVO = mapper.selectReportsUserAnswer(vo);

		switch(vo.getPollId()) {
			case "QES00000000000000001" :
				mapper.insertReportsScore(makePoll1(pollManageVO));
				break;
			case "QES00000000000000002" :
				mapper.insertReportsScore(makePoll2_1(pollManageVO));
				mapper.insertReportsScore(makePoll2_2(pollManageVO));
				break;
			case "QES00000000000000003" :
				mapper.insertReportsScore(makePoll3(pollManageVO));
				break;
		}
	}

	/**
	 * 마음알기 설문 완료 상태 조회
	 * @return
	 */
	@Override
	public int selectIsDone(PollManageVO pollManageVO) {

		return mapper.selectIsDone(pollManageVO);
	}

	/**
	 * 마음알기 설문 안내 사항 조회(기본데이터)
	 * @return pollNoticeDTL
	 */
	public PollNoticeDTO selectReportsNotice(String pollId) {
		return mapper.selectReportsNotice(pollId);
	}

	/**
	 * 보고서 목록 상태 생성
	 * @param list
	 * @param se
	 * @return
	 */
	public HashMap<String, List<PollDTO>> makeReportsStatus(List<PollDTO> list, String se) {
		List<PollDTO> todo = new ArrayList<>();
		List<PollDTO> done = new ArrayList<>();

		if(StringUtils.isNotEmpty(se) && se.equals("teacher")) {
			for(PollDTO dto : list) {
				if(dto.getExpired()) {
					done.add(dto);
				} else {
					todo.add(dto);
				}
			}
		}

		if(StringUtils.isNotEmpty(se) && se.equals("student")) {
			for(PollDTO dto : list) {
				if(dto.getStatus().equals("done")) {
					done.add(dto);
				} else {
					todo.add(dto);
				}
			}
		}

		return new HashMap<String, List<PollDTO>>(){{
			put("todo", todo);
			put("done", done);
		}};
	}

	/**
	 * 랜덤 문제 문자열 생성
	 * @param size
	 * @return
	 */
	public String makeRandomString(int size) {

		List<Integer> numbers = new ArrayList<>();

		for(int i=1; i<=size; i++) {
			numbers.add(i);
		}

		Collections.shuffle(numbers);

		StringBuilder sb = new StringBuilder();

		for(int i=0; i < size; i++) {
			sb.append(numbers.get(i));
			if(i < size-1) {
				sb.append(",");
			}
		}

		return sb.toString();
	}

	/**
	 * 회원 마음알기 설문 상세 함수
	 * @param pollManageVO
	 * @return
	 */
	@Override
	public PollDtlDTO selectReportsDtlItm(PollManageVO pollManageVO) {
		if(StringUtils.isEmpty(pollManageVO.getAuthorization()) || StringUtils.isEmpty(pollManageVO.getPollId())) {
			return PollDtlDTO.builder().build();
		}

		PollManageVO vo = selectReportsDtl(pollManageVO);

		StringBuilder stepList = new StringBuilder();

		for(int i=0; i<vo.getQesitmSnList().length; i++) {
			stepList.append(vo.getQesitmSnList()[i])
					.append(":");

			if(i < vo.getQesitmAnswerList().length) {
				stepList.append(vo.getQesitmAnswerList()[i]);
			}
			stepList.append("/");

			if(i < vo.getQesitmAnswerImageList().length) {
				stepList.append(vo.getQesitmAnswerImageList()[i]);
			}
			stepList.append(",");

			if(i == vo.getQesitmSnList().length - 1) {
				stepList.deleteCharAt(stepList.length() - 1);
			}
		}

		return PollDtlDTO.builder()
				.nttNo(vo.getNttNo())
				.status(vo.getStatus())
				.pollNm(vo.getPollNm())
				.startDate(vo.getStartDate())
				.endDate(vo.getEndDate())
				.step(stepList.toString())
				.metadata(makeMetaList(pollManageVO))
				.isSave(vo.getIsSave())
				.isVoice(vo.getIsVoice())
				.build();
	}

	/**
	 * 마음알기 설문 Q(질문), AT(답변문자), AI(답변이미지) 메타데이터 생성
	 * @param pollManageVO
	 * @return
	 */
	public ArrayList<PollMetaDTO> makeMetaList(PollManageVO pollManageVO) {

		List<PollManageVO> qnaList = selectReportsQnA(pollManageVO);

		ArrayList<PollMetaDTO> metaList = new ArrayList<PollMetaDTO>();

		for(PollManageVO qna : qnaList) {
			ArrayList<String> answerText = new ArrayList<String>();
			ArrayList<String> answerImage = new ArrayList<String>();

			for(String at : qna.getQesitmAnswerText().split(",")) {
				answerText.add(at);
			}

			for(String ai : qna.getQesitmAnswerImage().split(",")) {
				answerImage.add(ai);
			}

			metaList.add(
					PollMetaDTO.builder()
							.Q(qna.getQesitmSj())
							.AT(answerText)
							.AI(answerImage)
							.build()
			);
		}

		return metaList;
	}

	/**
	 * pollManageVO로 GneUserInfo 업데이트
	 * @param pollManageVO
	 */
	public void updateGneUserInfo(PollManageVO pollManageVO) {
		LoginVO vo = userManageService.selectUserInfo(LoginVO.builder().authorization(pollManageVO.getAuthorization()).build());
		userManageService.updateGneUserInfo(vo);
		insertReportsUserInfo(pollManageVO);
	}

	/**
	 * 회원 마음알기 점수 계산 및 가공(마음알기 설문1 : GM)
	 */
	public PollScoreDTO makePoll1(PollManageVO pollManageVO) {
		double score = Arrays.stream(pollManageVO.getQesitmAnswerList())
				.mapToInt(Integer::parseInt)
				.average()
				.orElse(0.0);

		return PollScoreDTO.builder()
				.authorization(pollManageVO.getAuthorization())
				.pollScore(score)
				.qesitmAnswerType("GM")
				.build();
	}

	/**
	 * 회원 마음알기 점수 계산 및 가공(마음알기 설문2_1 : IBP)
	 */
	public PollScoreDTO makePoll2_1(PollManageVO pollManageVO) {
		ArrayList<String> targetList = new ArrayList<>();

		for(int i=0; i<pollManageVO.getQesitmSnList().length; i++) {
			if(Integer.parseInt(pollManageVO.getQesitmSnList()[i]) <= 9) {
				targetList.add(pollManageVO.getQesitmAnswerList()[i]);
			}
		}

		double avg = targetList.stream()
				.mapToInt(num -> 5 - Integer.parseInt(num))
				.average()
				.orElse(0.0);

		return PollScoreDTO.builder()
				.authorization(pollManageVO.getAuthorization())
				.pollScore(avg)
				.qesitmAnswerType("IBP")
				.build();
	}

	/**
	 * 회원 마음알기 점수 계산 및 가공(마음알기 설문2_2 : EBP)
	 */
	public PollScoreDTO makePoll2_2(PollManageVO pollManageVO) {
		ArrayList<String> targetList = new ArrayList<>();

		for(int i=0; i<pollManageVO.getQesitmSnList().length; i++) {
			if(Integer.parseInt(pollManageVO.getQesitmSnList()[i]) > 9) {
				targetList.add(pollManageVO.getQesitmAnswerList()[i]);
			}
		}

		double avg = targetList.stream()
				.mapToInt(num -> 5 - Integer.parseInt(num))
				.average()
				.orElse(0.0);


		return PollScoreDTO.builder()
				.authorization(pollManageVO.getAuthorization())
				.pollScore(avg)
				.qesitmAnswerType("EBP")
				.build();
	}

	/**
	 * 회원 마음알기 점수 계산 및 가공(마음알기 설문3 : EK)
	 */
	public PollScoreDTO makePoll3(PollManageVO pollManageVO) {
		int[] compareVal = {2, 1, 4, 2};
		int sum = 0;

		for(int i=0; i<pollManageVO.getQesitmSnList().length; i++) {
			if(compareVal[Integer.parseInt(pollManageVO.getQesitmSnList()[i]) - 1] == Integer.parseInt(pollManageVO.getQesitmAnswerList()[i])) {
				sum += 1;
			}
		}

		return PollScoreDTO.builder()
				.authorization(pollManageVO.getAuthorization())
				.pollScore(sum)
				.qesitmAnswerType("EK")
				.build();
	}
}
