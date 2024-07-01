package egovframework.example.poll.service.impl;

import java.util.List;

import egovframework.example.poll.dto.PollDTO;
import egovframework.example.poll.dto.PollScoreDTO;
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
	List<PollDTO> selectReports(PollManageVO pollManageVO);
	
	/**
	 * 회원 마음알기 설문 선생님 목록 조회
	 * @return
	 */
	List<PollDTO> selectReportsTeacher();
	
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
	 * 회원 마음알기 설문 수정
	 * @param pollManageVO
	 */
	void updateReports(PollManageVO pollManageVO);
	
	/**
	 * 회원 마음알기 설문 상태 등록(PROGRESS)
	 * @param pollManageVO
	 */
	void insertReportsStatus(PollManageVO pollManageVO);

	/**
	 * 회원 마음알기 설문 유저정보 등록
	 * @param pollManageVO
	 */
	void insertReportsUserInfo(PollManageVO pollManageVO);

	/**
	 * 회원 마음알기 설문 답변 조회
	 * @param pollManageVO
	 * @return
	 */
	PollManageVO selectReportsUserAnswer(PollManageVO pollManageVO);
	
	/**
	 * 마음알기 설문 완료 상태 조회
	 * @return
	 */
	int selectIsDone(PollManageVO pollManageVO);
	
	/**
	 * 회원 마음알기 설문 상태 변경(DONE)
	 * @param pollManageVO
	 */
	void updateReportsStatus(PollManageVO pollManageVO);
	
	/**
	 * 마음알기 설문 문제 개수 조회
	 * @param pollManageVO
	 * @return
	 */
	int selectReportsCount(PollManageVO pollManageVO);

	/**
	 * 마음알기 설문 안내 사항 조회(기본데이터)
	 * @return pollNoticeDTL
	 */
	PollNoticeDTO selectReportsNotice(String pollId);
	
	/**
	 * 설문 관리 목록 기록 확인
	 * @return boolean
	 */
	int selectReportsMngIsExist(PollManageVO pollManageVO);

	/**
	 * 설문 점수 등록
	 * @param pollScoreDTO
	 */
	void insertReportsScore(PollScoreDTO pollScoreDTO);
}
