package egovframework.example.naver.dto;

import lombok.Data;

import java.util.List;

@Data
public class GneListDto<T> {
    /**
     * {
     *       "success" : false
     *     , "auth" : false
     *     , "code" : ""
     *     , "message" : "필수 파라메터 누락"
     *     , "data" : ""
     *     , "files" : ""
     *     , "wireData" : ""
     *     , "count" : 0
     * }
     */
    /**
     * {
     *       "success" : false
     *     , "auth" : false
     *     , "code" : ""
     *     , "message" : "사용자 정보 없음"
     *     , "data" : ""
     *     , "files" : ""
     *     , "wireData" : ""
     *     , "count" : 0
     * }
     */
    /**
     * 학생
     * {
     *       "success" : true
     *     , "auth" : false
     *     , "code" : ""
     *     , "message" : ""
     *     , "data" : {
     *           "schulCode" : "S333333"
     *         , "schulNm" : "경남시범초등학교"
     *         , "schulGradeCode" : "SCH_02"
     *         , "stdrYear" : "2024"
     *         , "userSeCode" : "04"
     *         , "stGrade" : "1"
     *         , "stClass" : "1"
     *         , "stNumber" : "9"
     *         , "userNm" : "손은찬"
     *         , "stClassNm" : ""
     *         , "userId" : "tlqjach-stu102@gne.go.kr"
     *         , "birth" : "2017-09-21"
     *         , "agencyTy" : "S"
     *         , "homeroomAt" : "N"
     *         , "txtbkList" : ""
     *         , "region" : "창원시(창원)"
     *         , "regionDetail" : "창원시"
     *         , "dedicatedYn" : ""
     *         , "confmSttusCode" : ""
     *         , "dedicatedDetail" : ""
     *     }
     *     , "files" : ""
     *     , "wireData" : ""
     *     , "count" : 1
     * }
     */
    /**
     * 선생님
     * {
     *       "success" : true
     *     , "auth" : false
     *     , "code" : ""
     *     , "message" : ""
     *     , "data" : {
     *           "schulCode" : "S333333"
     *         , "schulNm" : "경남시범초등학교"
     *         , "schulGradeCode" : "SCH_02"
     *         , "stdrYear" : "2024"
     *         , "userSeCode" : "08"
     *         , "stGrade" : "1"
     *         , "stClass" : "1"
     *         , "stNumber" : ""
     *         , "userNm" : "1학년1반 선생님"
     *         , "stClassNm" : ""
     *         , "userId" : "tlqjach11@gne.go.kr"
     *         , "birth" : ""
     *         , "agencyTy" : "S"
     *         , "homeroomAt" : "Y"
     *         , "txtbkList" : {
     *             "1" : [
     *                 {
     *                       "schulCode" : "S333333"
     *                     , "stdrYear" : "2024"
     *                     , "stGrade" : "1"
     *                     , "subjectId" : "E12_KOR_국어"
     *                     , "esntlId" : ""
     *                     , "subjectNm" : "국어"
     *                     , "rprsAt" : "Y"
     *                     , "txtbkId" : "E2022_E12_국어_1-1_CT_MOE"
     *                     , "txtbkNm" : "국어 1-1"
     *                     , "plscmpnCode" : "CT"
     *                     , "plscmpnNm" : "천재교과서"
     *                     , "authr" : "교육부"
     *                     , "txtbkSe" : "국정"
     *                     , "txtbkGrad" : "1"
     *                     , "courseCode" : "KOR"
     *                     , "courseNm" : "국어"
     *                     , "gradeGroup" : "E12"
     *                 }
     *                 http://1.213.164.252:60080/login?redirect=/
     *                 teacher001/test123
     *                 , {
     *                       "schulCode" : "S333333"
     *                     , "stdrYear":"2024","stGrade":"1","subjectId":"E12_MAT_수학","esntlId":"","subjectNm":"수학","rprsAt":"Y","txtbkId":"E2022_E12_수학_1-1_CT_MOE","txtbkNm":"수학 1-1","plscmpnCode":"CT","plscmpnNm":"천재교과서","authr":"교육부","txtbkSe":"국정","txtbkGrad":"1","courseCode":"MAT","courseNm":"수학","gradeGroup":"E12"}
     *                 , {
     *                       "schulCode":"S333333","stdrYear":"2024","stGrade":"1","subjectId":"E12_RWR_바슬즐","esntlId":"","subjectNm":"바슬즐","rprsAt":"Y","txtbkId":"E2022_E12_바슬즐_사람들_1-1_JH_MOE","txtbkNm":"바슬즐_사람들_1-1","plscmpnCode":"JH","plscmpnNm":"지학사","authr":"교육부","txtbkSe":"국정","txtbkGrad":"1","courseCode":"RWR","courseNm":"바슬즐","gradeGroup":"E12"}
     *                 , {
     *                     "schulCode":"S333333","stdrYear":"2024","stGrade":"1","subjectId":"E12_RWR_바슬즐","esntlId":"","subjectNm":"바슬즐","rprsAt":"Y","txtbkId":"E2022_E12_바슬즐_우리나라_1-1_JH_MOE","txtbkNm":"바슬즐_우리나라_1-1","plscmpnCode":"JH","plscmpnNm":"지학사","authr":"교육부","txtbkSe":"국정","txtbkGrad":"1","courseCode":"RWR","courseNm":"바슬즐","gradeGroup":"E12"}
     *                 , {
     *                     "schulCode":"S333333","stdrYear":"2024","stGrade":"1","subjectId":"E12_RWR_바슬즐","esntlId":"","subjectNm":"바슬즐","rprsAt":"Y","txtbkId":"E2022_E12_바슬즐_탐험_1-1_JH_MOE","txtbkNm":"바슬즐_탐험_1-1","plscmpnCode":"JH","plscmpnNm":"지학사","authr":"교육부","txtbkSe":"국정","txtbkGrad":"1","courseCode":"RWR","courseNm":"바슬즐","gradeGroup":"E12"}
     *                 , {
     *                     "schulCode":"S333333","stdrYear":"2024","stGrade":"1","subjectId":"E12_RWR_바슬즐","esntlId":"","subjectNm":"바슬즐","rprsAt":"Y","txtbkId":"E2022_E12_바슬즐_학교_1-1_JH_MOE","txtbkNm":"바슬즐_학교_1-1","plscmpnCode":"JH","plscmpnNm":"지학사","authr":"교육부","txtbkSe":"국정","txtbkGrad":"1","courseCode":"RWR","courseNm":"바슬즐","gradeGroup":"E12"}],"2":[{"schulCode":"S333333","stdrYear":"2024","stGrade":"2","subjectId":"E12_KOR_국어","esntlId":"","subjectNm":"국어","rprsAt":"Y","txtbkId":"E2022_E12_국어_2-1_CT_MOE","txtbkNm":"국어 2-1","plscmpnCode":"CT","plscmpnNm":"천재교과서","authr":"교육부","txtbkSe":"국정","txtbkGrad":"2","courseCode":"KOR","courseNm":"국어","gradeGroup":"E12"}
     *                 , {
     *                     "schulCode":"S333333","stdrYear":"2024","stGrade":"2","subjectId":"E12_MAT_수학","esntlId":"","subjectNm":"수학","rprsAt":"Y","txtbkId":"E2022_E12_수학_2-1_CT_MOE","txtbkNm":"수학 2-1","plscmpnCode":"CT","plscmpnNm":"천재교과서","authr":"교육부","txtbkSe":"국정","txtbkGrad":"2","courseCode":"MAT","courseNm":"수학","gradeGroup":"E12"
     *                 }
     *                 , {
     *                     "schulCode":"S333333","stdrYear":"2024","stGrade":"2","subjectId":"E12_RWR_바슬즐","esntlId":"","subjectNm":"바슬즐","rprsAt":"Y","txtbkId":"E2022_E12_바슬즐_마을_2-1_JH_MOE","txtbkNm":"바슬즐_마을_2-1","plscmpnCode":"JH","plscmpnNm":"지학사","authr":"교육부","txtbkSe":"국정","txtbkGrad":"2","courseCode":"RWR","courseNm":"바슬즐","gradeGroup":"E12"
     *                 }
     *             ]
     *             , "3" : [
     *                 {
     *                     "schulCode":"S333333","stdrYear":"2024","stGrade":"3","subjectId":"E34_ART_미술","esntlId":"","subjectNm":"미술","rprsAt":"Y","txtbkId":"E2015_E34_미술_3_KS_KJS","txtbkNm":"미술 3","plscmpnCode":"KS","plscmpnNm":"금성출판사","authr":"김정선","txtbkSe":"검정","txtbkGrad":"3","courseCode":"ART","courseNm":"미술","gradeGroup":"E34"
     *                 }
     *                 , {
     *                     "schulCode":"S333333","stdrYear":"2024","stGrade":"3","subjectId":"E34_ENG_영어","esntlId":"","subjectNm":"영어","rprsAt":"Y","txtbkId":"E2015_E34_영어_3_YB_KHL","txtbkNm":"영어 3","plscmpnCode":"YB","plscmpnNm":"와이비엠","authr":"김혜리","txtbkSe":"검정","txtbkGrad":"3","courseCode":"ENG","courseNm":"영어","gradeGroup":"E34"}
     *                 , {
     *                     "schulCode":"S333333","stdrYear":"2024","stGrade":"3","subjectId":"E34_KOR_국어","esntlId":"","subjectNm":"국어","rprsAt":"Y","txtbkId":"E2015_E34_국어_3-1_MR_MOE","txtbkNm":"국어 3-1","plscmpnCode":"MR","plscmpnNm":"미래엔","authr":"교육부","txtbkSe":"국정","txtbkGrad":"3","courseCode":"KOR","courseNm":"국어","gradeGroup":"E34"}
     *                 , {
     *                     "schulCode":"S333333","stdrYear":"2024","stGrade":"3","subjectId":"E34_KOR_국어","esntlId":"","subjectNm":"국어","rprsAt":"Y","txtbkId":"E2015_E34_국어_3-2_MR_MOE","txtbkNm":"국어 3-2","plscmpnCode":"MR","plscmpnNm":"미래엔","authr":"교육부","txtbkSe":"국정","txtbkGrad":"3","courseCode":"KOR","courseNm":"국어","gradeGroup":"E34"
     *                 }
     *                 , {
     *                     "schulCode":"S333333","stdrYear":"2024","stGrade":"3","subjectId":"E34_MAT_수학","esntlId":"","subjectNm":"수학","rprsAt":"Y","txtbkId":"E2015_E34_수학_3-1_DA_PGS","txtbkNm":"수학 3-1","plscmpnCode":"DA","plscmpnNm":"동아출판","authr":"박교식","txtbkSe":"검정","txtbkGrad":"3","courseCode":"MAT","courseNm":"수학","gradeGroup":"E34"
     *                 }
     *                 , {
     *                     "schulCode":"S333333","stdrYear":"2024","stGrade":"3","subjectId":"E34_SCI_과학","esntlId":"","subjectNm":"과학","rprsAt":"Y","txtbkId":"E2015_E34_과학_3-1_KS_PIU","txtbkNm":"과학 3-1","plscmpnCode":"KS","plscmpnNm":"금성출판사","authr":"박일우","txtbkSe":"검정","txtbkGrad":"3","courseCode":"SCI","courseNm":"과학","gradeGroup":"E34"
     *                 }
     *                 , {
     *                     "schulCode":"S333333","stdrYear":"2024","stGrade":"3","subjectId":"E34_SCI_과학","esntlId":"","subjectNm":"과학","rprsAt":"Y","txtbkId":"E2015_E34_과학_3-2_KS_PIU","txtbkNm":"과학 3-2","plscmpnCode":"KS","plscmpnNm":"금성출판사","authr":"박일우","txtbkSe":"검정","txtbkGrad":"3","courseCode":"SCI","courseNm":"과학","gradeGroup":"E34"
     *                 }
     *             ]
     *             ,"4":[
     *                 {
     *                     "schulCode":"S333333","stdrYear":"2024","stGrade":"4","subjectId":"E34_MUS_음악","esntlId":"","subjectNm":"음악","rprsAt":"Y","txtbkId":"E2015_E34_음악_4_KS_KYH","txtbkNm":"음악 4","plscmpnCode":"KS","plscmpnNm":"금성출판사","authr":"김용희","txtbkSe":"검정","txtbkGrad":"4","courseCode":"MUS","courseNm":"음악","gradeGroup":"E34"},{"schulCode":"S333333","stdrYear":"2024","stGrade":"4","subjectId":"E34_SCI_과학","esntlId":"","subjectNm":"과학","rprsAt":"Y","txtbkId":"E2015_E34_과학_4-1_IM_HDG","txtbkNm":"과학 4-1","plscmpnCode":"IM","plscmpnNm":"아이스크림미디어","authr":"현동걸","txtbkSe":"검정","txtbkGrad":"4","courseCode":"SCI","courseNm":"과학","gradeGroup":"E34"},{"schulCode":"S333333","stdrYear":"2024","stGrade":"4","subjectId":"E34_SCI_과학","esntlId":"","subjectNm":"과학","rprsAt":"Y","txtbkId":"E2015_E34_과학_4-2_IM_HDG","txtbkNm":"과학 4-2","plscmpnCode":"IM","plscmpnNm":"아이스크림미디어","authr":"현동걸","txtbkSe":"검정","txtbkGrad":"4","courseCode":"SCI","courseNm":"과학","gradeGroup":"E34"},{"schulCode":"S333333","stdrYear":"2024","stGrade":"4","subjectId":"E34_SOC_사회","esntlId":"","subjectNm":"사회","rprsAt":"Y","txtbkId":"E2015_E34_사회_4-1_IM_HCH","txtbkNm":"사회 4-1","plscmpnCode":"IM","plscmpnNm":"아이스크림미디어","authr":"한춘희","txtbkSe":"검정","txtbkGrad":"4","courseCode":"SOC","courseNm":"사회","gradeGroup":"E34"},{"schulCode":"S333333","stdrYear":"2024","stGrade":"4","subjectId":"E34_SOC_사회","esntlId":"","subjectNm":"사회","rprsAt":"Y","txtbkId":"E2015_E34_사회_4-2_IM_HCH","txtbkNm":"사회 4-2","plscmpnCode":"IM","plscmpnNm":"아이스크림미디어","authr":"한춘희","txtbkSe":"검정","txtbkGrad":"4","courseCode":"SOC","courseNm":"사회","gradeGroup":"E34"}],"5":[{"schulCode":"S333333","stdrYear":"2024","stGrade":"5","subjectId":"E56_ENG_영어","esntlId":"","subjectNm":"영어","rprsAt":"Y","txtbkId":"E2015_E56_영어_5_YB_KHL","txtbkNm":"영어 5","plscmpnCode":"YB","plscmpnNm":"와이비엠","authr":"김혜리","txtbkSe":"검정","txtbkGrad":"5","courseCode":"ENG","courseNm":"영어","gradeGroup":"E56"},{"schulCode":"S333333","stdrYear":"2024","stGrade":"5","subjectId":"E56_KOR_국어","esntlId":"","subjectNm":"국어","rprsAt":"Y","txtbkId":"E2015_E56_국어_5-1_MR_MOE","txtbkNm":"국어 5-1","plscmpnCode":"MR","plscmpnNm":"미래엔","authr":"교육부","txtbkSe":"국정","txtbkGrad":"5","courseCode":"KOR","courseNm":"국어","gradeGroup":"E56"},{"schulCode":"S333333","stdrYear":"2024","stGrade":"5","subjectId":"E56_KOR_국어","esntlId":"","subjectNm":"국어","rprsAt":"Y","txtbkId":"E2015_E56_국어_5-2_MR_MOE","txtbkNm":"국어 5-2","plscmpnCode":"MR","plscmpnNm":"미래엔","authr":"교육부","txtbkSe":"국정","txtbkGrad":"5","courseCode":"KOR","courseNm":"국어","gradeGroup":"E56"},{"schulCode":"S333333","stdrYear":"2024","stGrade":"5","subjectId":"E56_MOR_도덕","esntlId":"","subjectNm":"도덕","rprsAt":"Y","txtbkId":"E2015_E56_도덕_5_JH_MOE","txtbkNm":"도덕 5","plscmpnCode":"JH","plscmpnNm":"지학사","authr":"교육부","txtbkSe":"국정","txtbkGrad":"5","courseCode":"MOR","courseNm":"도덕","gradeGroup":"E56"},{"schulCode":"S333333","stdrYear":"2024","stGrade":"5","subjectId":"E56_SOC_사회","esntlId":"","subjectNm":"사회","rprsAt":"Y","txtbkId":"E2015_E56_사회_5-1_KH_KWG","txtbkNm":"사회_5-1","plscmpnCode":"KH","plscmpnNm":"교학사","authr":"김왕근","txtbkSe":"검정","txtbkGrad":"5","courseCode":"SOC","courseNm":"사회","gradeGroup":"E56"},{"schulCode":"S333333","stdrYear":"2024","stGrade":"5","subjectId":"E56_SOC_사회","esntlId":"","subjectNm":"사회","rprsAt":"Y","txtbkId":"E2015_E56_사회_5-2_KH_KWG","txtbkNm":"사회_5-2","plscmpnCode":"KH","plscmpnNm":"교학사","authr":"김왕근","txtbkSe":"검정","txtbkGrad":"5","courseCode":"SOC","courseNm":"사회","gradeGroup":"E56"}
     *             ]
     *             , "6" : [
     *                 {"schulCode":"S333333","stdrYear":"2024","stGrade":"6","subjectId":"E56_ART_미술","esntlId":"","subjectNm":"미술","rprsAt":"Y","txtbkId":"E2015_E56_미술_6_AT_JSH","txtbkNm":"미술 6","plscmpnCode":"AT","plscmpnNm":"미술과생활","authr":"정선화","txtbkSe":"검정","txtbkGrad":"6","courseCode":"ART","courseNm":"미술","gradeGroup":"E56"},{"schulCode":"S333333","stdrYear":"2024","stGrade":"6","subjectId":"E56_ENG_영어","esntlId":"","subjectNm":"영어","rprsAt":"Y","txtbkId":"E2015_E56_영어_6_DK_LJG","txtbkNm":"영어 6","plscmpnCode":"DK","plscmpnNm":"대교","authr":"이재근","txtbkSe":"검정","txtbkGrad":"6","courseCode":"ENG","courseNm":"영어","gradeGroup":"E56"},{"schulCode":"S333333","stdrYear":"2024","stGrade":"6","subjectId":"E56_KOR_국어","esntlId":"","subjectNm":"국어","rprsAt":"Y","txtbkId":"E2015_E56_국어_6-1_MR_MOE","txtbkNm":"국어 6-1","plscmpnCode":"MR","plscmpnNm":"미래엔","authr":"교육부","txtbkSe":"국정","txtbkGrad":"6","courseCode":"KOR","courseNm":"국어","gradeGroup":"E56"},{"schulCode":"S333333","stdrYear":"2024","stGrade":"6","subjectId":"E56_KOR_국어","esntlId":"","subjectNm":"국어","rprsAt":"Y","txtbkId":"E2015_E56_국어_6-2_MR_MOE","txtbkNm":"국어 6-2","plscmpnCode":"MR","plscmpnNm":"미래엔","authr":"교육부","txtbkSe":"국정","txtbkGrad":"6","courseCode":"KOR","courseNm":"국어","gradeGroup":"E56"},{"schulCode":"S333333","stdrYear":"2024","stGrade":"6","subjectId":"E56_MAT_수학","esntlId":"","subjectNm":"수학","rprsAt":"Y","txtbkId":"E2015_E56_수학_6-1_KS_RHC","txtbkNm":"수학_6-1","plscmpnCode":"KS","plscmpnNm":"금성출판사","authr":"류희찬","txtbkSe":"검정","txtbkGrad":"6","courseCode":"MAT","courseNm":"수학","gradeGroup":"E56"},{"schulCode":"S333333","stdrYear":"2024","stGrade":"6","subjectId":"E56_MAT_수학","esntlId":"","subjectNm":"수학","rprsAt":"Y","txtbkId":"E2015_E56_수학_6-2_KS_RHC","txtbkNm":"수학_6-2","plscmpnCode":"KS","plscmpnNm":"금성출판사","authr":"류희찬","txtbkSe":"검정","txtbkGrad":"6","courseCode":"MAT","courseNm":"수학","gradeGroup":"E56"},{"schulCode":"S333333","stdrYear":"2024","stGrade":"6","subjectId":"E56_MOR_도덕","esntlId":"","subjectNm":"도덕","rprsAt":"Y","txtbkId":"E2015_E56_도덕_6_JH_MOE","txtbkNm":"도덕 6","plscmpnCode":"JH","plscmpnNm":"지학사","authr":"교육부","txtbkSe":"국정","txtbkGrad":"6","courseCode":"MOR","courseNm":"도덕","gradeGroup":"E56"},{"schulCode":"S333333","stdrYear":"2024","stGrade":"6","subjectId":"E56_PHY_체육","esntlId":"","subjectNm":"체육","rprsAt":"Y","txtbkId":"E2015_E56_체육_6_KH_KBC","txtbkNm":"체육 6","plscmpnCode":"KH","plscmpnNm":"교학사","authr":"김방출","txtbkSe":"검정","txtbkGrad":"6","courseCode":"PHY","courseNm":"체육","gradeGroup":"E56"}
     *             ]
     *         }
     *         , "region" : "창원시(창원)"
     *         , "regionDetail" : "창원시"
     *         , "dedicatedYn" : "Y"
     *         , "confmSttusCode" : "C"
     *         , "dedicatedDetail" : [{"subject":"국어","gradeList":{"5":["4","5","6"]}},{"subject":"도덕","gradeList":{"5":["1","2","3"],"6":["1","2","3","4","5"]}}]},"files":"","wireData":"","count":1
     *     }
     */
    private boolean success;
    private boolean auth;
    private String code;
    private String message;
    private List<T> data;
}

