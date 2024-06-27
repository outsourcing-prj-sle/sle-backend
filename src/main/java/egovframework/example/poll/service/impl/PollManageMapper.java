package egovframework.example.poll.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import egovframework.example.cmmn.service.LoginVO;
import egovframework.example.poll.service.PollManageVO;
import egovframework.example.poll.dto.PollNoticeDTO;

@Mapper("pollManageMapper")
public interface PollManageMapper {
	/**
	 * 회원 마음알기 설문 목록 조회
	 * @param pollManageVO
	 * @return
	 */
	List<PollManageVO> selectReports(PollManageVO pollManageVO);
	
	/**
	 * 회원 마음알기 설문 선생님 목록 조회
	 * @return
	 */
	List<PollManageVO> selectReportsTeacher();
	
	/**
	 * 회원 마음알기 설문 상세 조회
	 * @param pollManageVO
	 * @return
	 */
	PollManageVO selectReportsDtl(PollManageVO pollManageVO);
	
	/**
	 * 회원 마음알기 설문 문항 및 선지 조회
	 * @param pollManageVO
	 * @return
	 */
	List<PollManageVO> selectReportsQnA(PollManageVO pollManageVO);
	
	/**
	 * 회원 마음알기 설문 등록
	 * @param pollManageVO
	 */
	void insertReports(PollManageVO pollManageVO);
	
	/**
	 * 회원 마음알기 설문 상태 등록(PROGRESS)
	 * @param pollManageVO
	 */
	void insertReportsStatus(PollManageVO pollManageVO);
	
	/**
	 * 회원 마음알기 설문 점수 등록(GM)
	 * @param pollManageVO
	 */
	void insertReportsScore1(PollManageVO pollManageVO);
	
	/**
	 * 회원 마음알기 설문 점수 등록(IBP, EBP)
	 * @param pollManageVO
	 */
	void insertReportsScore2(PollManageVO pollManageVO);
	
	/**
	 * 회원 마음알기 설문 점수 등록(EK)
	 * @param pollManageVO
	 */
	void insertReportsScore3(PollManageVO pollManageVO);
	
	/**
	 * 마음알기 설문 완료 상태 조회
	 * @return
	 */
	int selectIsDone(PollManageVO pollManageVO);
	
	/**
	 * 회원 마음알기 설문 수정
	 * @param pollManageVO
	 */
	void updateReports(PollManageVO pollManageVO);
	
	/**
	 * 회원 마음알기 설문 상태 변경(DONE)
	 * @param pollManageVO
	 */
	void updateReportsStatus(PollManageVO pollManageVO);

	/**
	 * 회원 마음알기 설문 테이블 내에 유저 정보 저장
	 * @param pollManageVO
	 */
	void updateReportsUserInfo(PollManageVO pollManageVO);
	
	/**
	 * 마음알기 설문 문제 개수 조회
	 * @param pollManageVO
	 * @return
	 */
	int selectReportsCount(PollManageVO pollManageVO);
	
	/**
	 * 토큰으로 회원 인증
	 * @return boolean
	 */
	boolean authorizationUser(LoginVO loginVO);
	
	/**
	 * 마음알기 설문 안내 사항 조회(기본데이터)
	 * @return pollNoticeDTL
	 */
	PollNoticeDTO selectReportsNotice(String pollId);

	/**설문조사 기록 확인
	 * @return boolean
	 */
	int selectHistoryIsExist(PollManageVO pollManageVO);
	
	/**
	 * 설문 관리 목록 기록 확인
	 * @return boolean
	 */
	int selectReportsMngIsExist(PollManageVO pollManageVO);
}
