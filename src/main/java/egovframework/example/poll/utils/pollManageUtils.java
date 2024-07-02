package egovframework.example.poll.utils;

import egovframework.example.cmmn.service.LoginVO;
import egovframework.example.idadminpoll.service.IdAdminPollManageService;
import egovframework.example.idadminpoll.service.IdAdminPollManageVO;
import egovframework.example.user.service.UserManageService;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;

public class pollManageUtils {
    @Resource(name = "idAdminPollManageService")
    private IdAdminPollManageService pollManageService;

    @Resource(name = "userManageService")
    private UserManageService userManageService;

    /**
     * 허가 받은 설문 대상자 확인
     * @param userId
     * @param pollId
     * @return
     */
    public boolean isAllowedPoll(String userId, String pollId) {
        LoginVO userInfo = userManageService.selectUserInfo(LoginVO.builder().authorization(userId).build());
        IdAdminPollManageVO vo = pollManageService.selectIdAdminPollTargetList(pollId);

        for(String target : vo.getPollTargetList()) {
            String[] list = target.split("__");

            /**
             * 학교 비교
             */
            if(!list[0].equals("null") && !userInfo.getSchulCode().equals(list[0])) {
                continue;
            }

            /**
             * 학년급, 학년 비교
             */
            if(list[1].equals(userInfo.getSchulGradeCode())) {
                if(list[2].equals("null") || list[2].equals(userInfo.getClassCode())) {
                    return true;
                }
            }
        }

        return false;
    }
}
