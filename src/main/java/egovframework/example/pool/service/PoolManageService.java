package egovframework.example.pool.service;

import java.util.List;

public interface PoolManageService {
	/**
	 * 회원 마음알기 설문 목록 조회
	 * @param poolManageVO
	 * @return
	 */
	List<PoolManageVO> selectReports(PoolManageVO poolManageVO);
	
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
	PoolManageVO selectReportsQnA(PoolManageVO poolManageVO);
	
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
	 * 회원 마음알기 설문 상태 변경(DONE)
	 * @param poolManageVO
	 */
	void updateReportsStatus(PoolManageVO poolManageVO);
}
