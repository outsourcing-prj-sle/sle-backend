package egovframework.example.user.service.impl;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import egovframework.example.cmmn.service.LoginVO;
import egovframework.example.user.service.MySelVO;

@Mapper("userManageMapper")
public interface UserManageMapper {
	
	/**
	 * 회원정보 조회
	 * @return LoginVO 
	 */
	LoginVO selectUserInfo(LoginVO loginVO);
	
	/**
	 * ID로 회원 정보 조회
	 * @return boolean
	 */
	boolean checkUserById(@Param("id") String id);
	
	/**
	 * 회원정보 등록
	 */
	void insertUserInfo(LoginVO loginVO);
	
	/**
	 * 사용자보안설정 등록
	 */
	void insertUserScrtyEstbs(LoginVO loginVO);
	
	/**
	 * 회원정보 수정
	 */
	void updateUserInfo(LoginVO loginVO);
	
	/**
	 * 학생 SEL 목록 조회
	 */
	List<MySelVO> selectStudentSelList(LoginVO loginVO);
	
	/**
	 * 선생님 SEL 목록 조회
	 */
	List<MySelVO> selectTeacherSelList(LoginVO loginVO);
	
}
