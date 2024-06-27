package egovframework.example.poll.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.example.cmmn.service.LoginVO;
import egovframework.example.poll.service.PollManageService;
import egovframework.example.poll.service.PollManageVO;
import egovframework.example.poll.dto.PollNoticeDTO;

@Service("pollManageService")
public class PollManageServiceImpl implements PollManageService {
	
	@Resource(name = "pollManageMapper")
	private PollManageMapper mapper;
	
	/**
	 * 회원 마음알기 설문 목록 조회
	 * @param pollManageVO
	 * @return
	 */
	@Override
	public List<PollManageVO> selectReports(PollManageVO pollManageVO) {
		return mapper.selectReports(pollManageVO);
	}
	
	/**
	 * 회원 마음알기 설문 선생님 목록 조회
	 * @return
	 */
	@Override
	public List<PollManageVO> selectReportsTeacher() {
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
		
		if(mapper.selectHistoryIsExist(pollManageVO) == 1) {
			mapper.updateReports(pollManageVO);
		} else {
			mapper.insertReports(pollManageVO);
		}
	}

	/**
	 * 회원 마음알기 설문 상태 등록(PROGRESS)
	 * @param pollManageVO
	 */
	@Override
	public void insertReportsStatus(PollManageVO pollManageVO) {
		if(mapper.selectReportsMngIsExist(pollManageVO) == 0) {
			int size = mapper.selectReportsCount(pollManageVO);
			
			pollManageVO.setQesitmSnList(makeRandomString(size));
			
			mapper.insertReportsStatus(pollManageVO);
		}
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
	 * 회원 마음알기 설문 상태 변경(DONE)
	 * @param pollManageVO
	 */
	@Override
	public void updateReportsStatus(PollManageVO pollManageVO) {
		insertReports(pollManageVO);
		mapper.updateReportsStatus(pollManageVO);
		mapper.updateReportsUserInfo(pollManageVO);
		insertReportsScore(pollManageVO);
	}
	
	/**
	 * 회원 마음알기 점수 등록 함수
	 * @param vo
	 */
	public void insertReportsScore(PollManageVO vo) {
		String pollId = vo.getPollId();
		
		switch (pollId) {
		case "QES00000000000000001":
			mapper.insertReportsScore1(vo);			
			break;
		case "QES00000000000000002":
			mapper.insertReportsScore2(vo);			
			break;
		case "QES00000000000000003":
			mapper.insertReportsScore3(vo);			
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
	 * 토큰으로 회원 인증
	 * @return boolean
	 */
	public boolean authorizationUser(LoginVO loginVO) {
		
		return mapper.authorizationUser(loginVO);
	}
	
	/**
	 * 마음알기 설문 안내 사항 조회(기본데이터)
	 * @return pollNoticeDTL
	 */
	public PollNoticeDTO selectReportsNotice(String pollId) {
		return mapper.selectReportsNotice(pollId);
	}
}
