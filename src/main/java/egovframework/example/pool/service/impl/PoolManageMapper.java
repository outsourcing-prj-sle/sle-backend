package egovframework.example.pool.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import egovframework.example.cmmn.service.LoginVO;
import egovframework.example.pool.service.PoolManageVO;

@Mapper("poolManageMapper")
public interface PoolManageMapper {
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
	 * 회원 마음알기 설문 상태 변경(DONE)
	 * @param poolManageVO
	 */
	void updateReportsStatus(PoolManageVO poolManageVO);
	
	/**
	 * 마음알기 설문 문제 개수 조회
	 * @param poolManageVO
	 * @return
	 */
	int selectReportsCount(PoolManageVO poolManageVO);
	
	/**
	 * 마음알기 설문 완료 상태 조회
	 * @return
	 */
	int selectIsDone(PoolManageVO poolManageVO);
	
	/**
	 * 토큰으로 회원 인증
	 * @return boolean
	 */
	boolean authorizationUser(LoginVO loginVO);
}
