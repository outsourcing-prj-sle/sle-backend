package egovframework.example.naver.dto;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class GneSchoolUserDto {
    /**
     * {
     *       "success" : true
     *     , "auth" : false
     *     , "code" : ""
     *     , "message" : ""
     *     , "data" : [
     *         {
     *               "schulCode" : "S333333"
     *             , "schulNm":"경남시범초등학교"
     *             , "schulGradeCode" : "SCH_02"
     *             , "stdrYear" : "2024"
     *             , "userSeCode" : "04"
     *             , "stGrade" : "1"
     *             , "stClass" : "1"
     *             , "stNumber" : "2"
     *             , "userNm" : "초학생61"
     *             , "stClassNm" : ""
     *             , "userId" : ""
     *             , "birth" : ""
     *             , "agencyTy" : ""
     *             , "homeroomAt" : ""
     *             , "txtbkList" : ""
     *             , "region" : ""
     *             , "regionDetail" : ""
     *             , "dedicatedYn" : ""
     *             , "confmSttusCode" : ""
     *             , "dedicatedDetail" : ""
     *         }
     *         , {"schulCode":"S333333","schulNm":"경남시범초등학교","schulGradeCode":"SCH_02","stdrYear":"2024","userSeCode":"04","stGrade":"1","stClass":"1","stNumber":"4","userNm":"최두원","stClassNm":"","userId":"","birth":"","agencyTy":"","homeroomAt":"","txtbkList":"","region":"","regionDetail":"","dedicatedYn":"","confmSttusCode":"","dedicatedDetail":""},{"schulCode":"S333333","schulNm":"경남시범초등학교","schulGradeCode":"SCH_02","stdrYear":"2024","userSeCode":"04","stGrade":"1","stClass":"1","stNumber":"6","userNm":"초학생7","stClassNm":"","userId":"","birth":"","agencyTy":"","homeroomAt":"","txtbkList":"","region":"","regionDetail":"","dedicatedYn":"","confmSttusCode":"","dedicatedDetail":""},{"schulCode":"S333333","schulNm":"경남시범초등학교","schulGradeCode":"SCH_02","stdrYear":"2024","userSeCode":"04","stGrade":"1","stClass":"1","stNumber":"7","userNm":"홍은철","stClassNm":"","userId":"","birth":"","agencyTy":"","homeroomAt":"","txtbkList":"","region":"","regionDetail":"","dedicatedYn":"","confmSttusCode":"","dedicatedDetail":""},{"schulCode":"S333333","schulNm":"경남시범초등학교","schulGradeCode":"SCH_02","stdrYear":"2024","userSeCode":"04","stGrade":"1","stClass":"1","stNumber":"8","userNm":"초학생9","stClassNm":"","userId":"","birth":"","agencyTy":"","homeroomAt":"","txtbkList":"","region":"","regionDetail":"","dedicatedYn":"","confmSttusCode":"","dedicatedDetail":""},{"schulCode":"S333333","schulNm":"경남시범초등학교","schulGradeCode":"SCH_02","stdrYear":"2024","userSeCode":"04","stGrade":"1","stClass":"1","stNumber":"9","userNm":"손은찬","stClassNm":"","userId":"tlqjach-stu102@gne.go.kr","birth":"2017-09-21","agencyTy":"S","homeroomAt":"","txtbkList":"","region":"","regionDetail":"","dedicatedYn":"","confmSttusCode":"","dedicatedDetail":""},{"schulCode":"S333333","schulNm":"경남시범초등학교","schulGradeCode":"SCH_02","stdrYear":"2024","userSeCode":"04","stGrade":"1","stClass":"1","stNumber":"10","userNm":"초학생11","stClassNm":"","userId":"","birth":"","agencyTy":"","homeroomAt":"","txtbkList":"","region":"","regionDetail":"","dedicatedYn":"","confmSttusCode":"","dedicatedDetail":""},{"schulCode":"S333333","schulNm":"경남시범초등학교","schulGradeCode":"SCH_02","stdrYear":"2024","userSeCode":"04","stGrade":"1","stClass":"1","stNumber":"12","userNm":"초학생13","stClassNm":"","userId":"","birth":"","agencyTy":"","homeroomAt":"","txtbkList":"","region":"","regionDetail":"","dedicatedYn":"","confmSttusCode":"","dedicatedDetail":""},{"schulCode":"S333333","schulNm":"경남시범초등학교","schulGradeCode":"SCH_02","stdrYear":"2024","userSeCode":"04","stGrade":"1","stClass":"1","stNumber":"13","userNm":"초학생14","stClassNm":"","userId":"","birth":"","agencyTy":"","homeroomAt":"","txtbkList":"","region":"","regionDetail":"","dedicatedYn":"","confmSttusCode":"","dedicatedDetail":""},{"schulCode":"S333333","schulNm":"경남시범초등학교","schulGradeCode":"SCH_02","stdrYear":"2024","userSeCode":"04","stGrade":"1","stClass":"1","stNumber":"99","userNm":"초학생7","stClassNm":"","userId":"","birth":"","agencyTy":"","homeroomAt":"","txtbkList":"","region":"","regionDetail":"","dedicatedYn":"","confmSttusCode":"","dedicatedDetail":""},{"schulCode":"S333333","schulNm":"경남시범초등학교","schulGradeCode":"SCH_02","stdrYear":"2024","userSeCode":"04","stGrade":"1","stClass":"6","stNumber":"1","userNm":"최연경","stClassNm":"","userId":"tlqjach-stu101@gne.go.kr","birth":"2018-01-18","agencyTy":"S","homeroomAt":"","txtbkList":"","region":"","regionDetail":"","dedicatedYn":"","confmSttusCode":"","dedicatedDetail":""},{"schulCode":"S333333","schulNm":"경남시범초등학교","schulGradeCode":"SCH_02","stdrYear":"2024","userSeCode":"04","stGrade":"3","stClass":"3","stNumber":"1","userNm":"삼삼일","stClassNm":"","userId":"","birth":"","agencyTy":"","homeroomAt":"","txtbkList":"","region":"","regionDetail":"","dedicatedYn":"","confmSttusCode":"","dedicatedDetail":""},{"schulCode":"S333333","schulNm":"경남시범초등학교","schulGradeCode":"SCH_02","stdrYear":"2024","userSeCode":"04","stGrade":"3","stClass":"3","stNumber":"3","userNm":"삼삼삼","stClassNm":"","userId":"","birth":"","agencyTy":"","homeroomAt":"","txtbkList":"","region":"","regionDetail":"","dedicatedYn":"","confmSttusCode":"","dedicatedDetail":""},{"schulCode":"S333333","schulNm":"경남시범초등학교","schulGradeCode":"SCH_02","stdrYear":"2024","userSeCode":"04","stGrade":"3","stClass":"3","stNumber":"4","userNm":"삼삼사","stClassNm":"","userId":"","birth":"","agencyTy":"","homeroomAt":"","txtbkList":"","region":"","regionDetail":"","dedicatedYn":"","confmSttusCode":"","dedicatedDetail":""},{"schulCode":"S333333","schulNm":"경남시범초등학교","schulGradeCode":"SCH_02","stdrYear":"2024","userSeCode":"04","stGrade":"3","stClass":"3","stNumber":"5","userNm":"삼삼오","stClassNm":"","userId":"","birth":"","agencyTy":"","homeroomAt":"","txtbkList":"","region":"","regionDetail":"","dedicatedYn":"","confmSttusCode":"","dedicatedDetail":""},{"schulCode":"S333333","schulNm":"경남시범초등학교","schulGradeCode":"SCH_02","stdrYear":"2024","userSeCode":"04","stGrade":"3","stClass":"3","stNumber":"6","userNm":"삼삼육","stClassNm":"","userId":"","birth":"","agencyTy":"","homeroomAt":"","txtbkList":"","region":"","regionDetail":"","dedicatedYn":"","confmSttusCode":"","dedicatedDetail":""},{"schulCode":"S333333","schulNm":"경남시범초등학교","schulGradeCode":"SCH_02","stdrYear":"2024","userSeCode":"04","stGrade":"3","stClass":"3","stNumber":"7","userNm":"삼삼칠","stClassNm":"","userId":"","birth":"","agencyTy":"","homeroomAt":"","txtbkList":"","region":"","regionDetail":"","dedicatedYn":"","confmSttusCode":"","dedicatedDetail":""},{"schulCode":"S333333","schulNm":"경남시범초등학교","schulGradeCode":"SCH_02","stdrYear":"2024","userSeCode":"04","stGrade":"3","stClass":"3","stNumber":"8","userNm":"삼삼팔","stClassNm":"","userId":"","birth":"","agencyTy":"","homeroomAt":"","txtbkList":"","region":"","regionDetail":"","dedicatedYn":"","confmSttusCode":"","dedicatedDetail":""},{"schulCode":"S333333","schulNm":"경남시범초등학교","schulGradeCode":"SCH_02","stdrYear":"2024","userSeCode":"04","stGrade":"3","stClass":"3","stNumber":"10","userNm":"삼삼공","stClassNm":"","userId":"","birth":"","agencyTy":"","homeroomAt":"","txtbkList":"","region":"","regionDetail":"","dedicatedYn":"","confmSttusCode":"","dedicatedDetail":""},{"schulCode":"S333333","schulNm":"경남시범초등학교","schulGradeCode":"SCH_02","stdrYear":"2024","userSeCode":"04","stGrade":"6","stClass":"6","stNumber":"15","userNm":"66홍길동15","stClassNm":"","userId":"","birth":"","agencyTy":"","homeroomAt":"","txtbkList":"","region":"","regionDetail":"","dedicatedYn":"","confmSttusCode":"","dedicatedDetail":""}],"files":"","wireData":"","count":20
     * }
     */
    private String schulCode;      // 학교코드
    private String schulNm;        // 학교명
    private String schulGradeCode; // 학교급코드
    private String stdrYear;       // 기준년도
    private String userSeCode;     // 사용자구분코드(04: 학생, 08: 교사)
    private String stGrade;        // 학년
    private String stClass;        // 반
    private String stNumber;       // 번호
    private String userNm;         // 사용자명
    private String stClassNm;      // 반이름
    private String userId;         // 사용자ID
    private String birth;          // 생일(YYYY-MM-DD)
    private String agencyTy;       // 기관타입(S: 학교, A: 기관)
}

