package egovframework.example.idadminpoll.service;

import egovframework.example.cmmn.service.PaginationVO;
import lombok.*;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IdAdminPollManageVO extends PaginationVO {

    /**
     * 검색어
     */
    private String searchKeyword;

    /**
     * 사용자 고유 아이디
     */
    private String uniqId;

    /**
     * 설문 아이디
     */
    private String pollId;

    /**
     * 설문명
     */
    private String pollNm;

    /**
     * 설문 시작일
     */
    private String pollBgnde;

    /**
     * 설문 종료일
     */
    private String pollEndde;

    /**
     * 설문 대상
     */
    private String pollTarget;

    /**
     * 설문 대상 배열
     */
    private String[] pollTargetList;

    /**
     * 학교명
     */
    private String schulNm;

    /**
     * 학교 코드
     */
    private String schulCode;

    /**
     * 학년
     */
    private String stGrade;

    /**
     * 사용자명
     */
    private String userNm;

    /**
     * 성별
     */
    private String sexdstn;

    /**
     * 생년월일
     */
    private String brthdy;

    /**
     * 이메일 주소
     */
    private String emailAdres;

    /**
     * 설문 답변 순번
     */
    private String qesitmSn;

    /**
     * 설문 답변 이미지
     */
    private String qesitmAnswerImage;

    /**
     * 설문 답변
     */
    private String qesitmAnswer;

    /**
     * 학교급 코드
     */
    private String schulGradeCode;

    /**
     * 설문 순번 배열
     */
    private String[] qesitmSnList;

    /**
     * 설문 답변 이미지 배열
     */
    private String[] qesitmAnswerImageList;

    /**
     * 설문 답변 배열
     */
    private String[] qesitmAnswerList;

    public String[] getPollTargetList() {

        if(StringUtils.isEmpty(this.pollTarget)) {
            return null;
        }

        return pollTarget.split(",");
    }

    public String[] getQesitmSnList() {

        if(StringUtils.isEmpty(this.qesitmSn)) {
            return null;
        }

        return qesitmSn.split(",");
    }

    public String[] getQesitmAnswerImageList() {

        if(StringUtils.isEmpty(this.qesitmAnswerImage)) {
            return null;
        }

        return qesitmAnswerImage.split(",");
    }

    public String[] getQesitmAnswerList() {

        if(StringUtils.isEmpty(this.qesitmAnswer)) {
            return null;
        }

        return qesitmAnswer.split(",");
    }
}
