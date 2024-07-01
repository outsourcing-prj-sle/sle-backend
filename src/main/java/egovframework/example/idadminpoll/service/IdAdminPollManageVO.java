package egovframework.example.idadminpoll.service;

import lombok.*;
import org.apache.commons.lang3.StringUtils;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IdAdminPollManageVO {
    private String userId;
    private String pollId;
    private String pollNm;
    private String pollBgnde;
    private String pollEndde;
    private String pollTarget;
    private String[] pollTargetList;
    private String schulNm;
    private String stGrade;
    private String userNm;
    private String sexdstn;
    private String brthy;
    private String emailAdres;
    private String qesitmSn;
    private String qesitmAnswerImage;
    private String qesitmAnswer;
    private String[] qesitmSnList;
    private String[] qesitmAnswerImageList;
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
