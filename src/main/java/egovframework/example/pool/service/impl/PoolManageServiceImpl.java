package egovframework.example.pool.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.egovframe.rte.fdl.cmmn.exception.FdlException;
import org.egovframe.rte.fdl.idgnr.EgovIdGnrService;
import org.springframework.stereotype.Service;

import egovframework.example.pool.service.PoolManageService;
import egovframework.example.pool.service.PoolManageVO;

@Service("poolManageService")
public class PoolManageServiceImpl implements PoolManageService {
	
	@Resource(name = "poolManageMapper")
	private PoolManageMapper mapper;
	
	/**
	 * 회원 마음알기 설문 목록 조회
	 * @param poolManageVO
	 * @return
	 */
	@Override
	public List<PoolManageVO> selectReports(PoolManageVO poolManageVO) {
		return mapper.selectReports(poolManageVO);
	}
	
	/**
	 * 회원 마음알기 설문 선생님 목록 조회
	 * @param poolManageVO
	 * @return
	 */
	@Override
	public List<PoolManageVO> selectReportsTeacher(PoolManageVO poolManageVO) {
		return mapper.selectReportsTeacher(poolManageVO);
	}

	/**
	 * 회원 마음알기 설문 상세 조회
	 * @param poolManageVO
	 * @return
	 */
	@Override
	public PoolManageVO selectReportsDtl(PoolManageVO poolManageVO) {
		return mapper.selectReportsDtl(poolManageVO);
	}

	/**
	 * 회원 마음알기 설문 문항 및 선지 조회
	 * @param poolManageVO
	 * @return
	 */
	@Override
	public List<PoolManageVO> selectReportsQnA(PoolManageVO poolManageVO) {
		return mapper.selectReportsQnA(poolManageVO);
	}

	/**
	 * 회원 마음알기 설문 등록
	 * @param poolManageVO
	 */
	@Override
	public void insertReports(PoolManageVO poolManageVO) {
		mapper.insertReports(poolManageVO);		
	}

	/**
	 * 회원 마음알기 설문 상태 등록(PROGRESS)
	 * @param poolManageVO
	 */
	@Override
	public void insertReportsStatus(PoolManageVO poolManageVO) {		
		int size = mapper.selectReportsCount(poolManageVO);
		
		poolManageVO.setQesitmSnList(makeRandomString(size));
		
		mapper.insertReportsStatus(poolManageVO);
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
	 * @param poolManageVO
	 */
	@Override
	public void updateReportsStatus(PoolManageVO poolManageVO) {
		mapper.updateReportsStatus(poolManageVO);
	}

	/**
	 * 마음알기 설문 완료 상태 조회
	 * @return
	 */
	@Override
	public int selectIsDone(PoolManageVO poolManageVO) {
		
		return mapper.selectIsDone(poolManageVO);
	} 
	
	
}
