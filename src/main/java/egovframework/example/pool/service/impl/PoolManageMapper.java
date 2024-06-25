package egovframework.example.pool.service.impl;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import egovframework.example.cmmn.service.LoginVO;
import egovframework.example.pool.service.PoolManageVO;
import egovframework.example.pool.service.PoolNoticeDTO;

@Mapper("poolManageMapper")
public interface PoolManageMapper {
	/**
	 * 회원 마음알기 설문 목록 조회
	 * @param poolManageVO
	 * @return
	 */
	List<PoolManageVO> selectReports(PoolManageVO poolManageVO);
	
	/**
	 * 회원 마음알기 설문 선생님 목록 조회
	 * @param poolManageVO
	 * @return
	 */
	List<PoolManageVO> selectReportsTeacher(PoolManageVO poolManageVO);
	
	/**
	 * 회원 마음알기 설문 상세 조회
	 * @param poolManageVO
	 * @return
	 */
	PoolManageVO selectReportsDtl(PoolManageVO poolManageVO);
	
	/**
	 * 회원 마음알기 설문 문항 및 선지 조회
	 * @param poolManageVO
	 * @return
	 */
	List<PoolManageVO> selectReportsQnA(PoolManageVO poolManageVO);
	
	/**
	 * 회원 마음알기 설문 등록
	 * @param poolManageVO
	 */
	void insertReports(PoolManageVO poolManageVO);
	
	/**
	 * 회원 마음알기 설문 상태 등록(PROGRESS)
	 * @param poolManageVO
	 */
	void insertReportsStatus(PoolManageVO poolManageVO);
	
	/**
	 * 회원 마음알기 설문 점수 등록(GM)
	 * @param poolManageVO
	 */
	void insertReportsScore1(PoolManageVO poolManageVO);
	
	/**
	 * 회원 마음알기 설문 점수 등록(IBP, EBP)
	 * @param poolManageVO
	 */
	void insertReportsScore2(PoolManageVO poolManageVO);
	
	/**
	 * 회원 마음알기 설문 점수 등록(EK)
	 * @param poolManageVO
	 */
	void insertReportsScore3(PoolManageVO poolManageVO);
	
	/**
	 * 마음알기 설문 완료 상태 조회
	 * @return
	 */
	int selectIsDone(PoolManageVO poolManageVO);
	
	/**
	 * 회원 마음알기 설문 수정
	 * @param poolManageVO
	 */
	void updateReports(PoolManageVO poolManageVO);
	
	/**
	 * 회원 마음알기 설문 상태 변경(DONE)
	 * @param poolManageVO
	 */
	void updateReportsStatus(PoolManageVO poolManageVO);

	/**
	 * 회원 마음알기 설문 테이블 내에 유저 정보 저장
	 * @param poolManageVO
	 */
	void updateReportsUserInfo(PoolManageVO poolManageVO);
	
	/**
	 * 마음알기 설문 문제 개수 조회
	 * @param poolManageVO
	 * @return
	 */
	int selectReportsCount(PoolManageVO poolManageVO);
	
	/**
	 * 토큰으로 회원 인증
	 * @return boolean
	 */
	boolean authorizationUser(LoginVO loginVO);
	
	/**
	 * 마음알기 설문 안내 사항 조회(기본데이터)
	 * @return PoolNoticeDTL
	 */
	PoolNoticeDTO selectReportsNotice(String pollId);

	/**설문조사 기록 확인
	 * @return boolean
	 */
	int selectHistoryIsExist(PoolManageVO poolManageVO);
	
	/**
	 * 설문 관리 목록 기록 확인
	 * @return boolean
	 */
	int selectReportsMngIsExist(PoolManageVO poolManageVO);
}
