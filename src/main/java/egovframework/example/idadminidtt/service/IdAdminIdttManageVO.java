package egovframework.example.idadminidtt.service;

import egovframework.example.cmmn.service.PaginationVO;
import lombok.*;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IdAdminIdttManageVO extends PaginationVO {
    /**
     * 학교명
     */
    private String schulNm;

    /**
     * 사용자명
     */
    private String userNm;

    /**
     * 학년, 반 정보
     */
    private String userSpaceOrgInfo;

    /**
     * 설문조사 유형
     */
    private String qesType;

    /**
     * 설문조사 답변
     */
    private String qesAnswer;

    /**
     * 설문조사 답변 배열
     */
    private String[] qesAnswerList;

    public String[] getQesAnswerList() {
        if(StringUtils.isEmpty(qesAnswer)) {
            return new String[0];
        }

        return qesAnswer.split("/");
    }
}
