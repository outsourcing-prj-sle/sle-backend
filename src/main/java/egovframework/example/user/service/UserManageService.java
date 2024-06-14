package egovframework.example.user.service;

import java.util.List;

import egovframework.example.cmmn.service.LoginVO;

public interface UserManageService {
	
	/**
	 * 회원정보 조회
	 */
	LoginVO selectUserInfo(LoginVO loginVO);
	
	/**
	 * 회원정보 등록
	 */
	void insertUserInfo(LoginVO loginVO);
	
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
