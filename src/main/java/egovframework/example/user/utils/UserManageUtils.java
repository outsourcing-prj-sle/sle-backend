package egovframework.example.user.utils;

import egovframework.example.cmmn.service.LoginVO;
import egovframework.example.naver.dto.GneInfoDto;
import egovframework.example.naver.dto.GneUserDto;
import egovframework.example.naver.service.NaverService;
import egovframework.example.poll.service.PollManageService;
import egovframework.example.poll.service.PollManageVO;
import egovframework.example.user.dto.MySelTeacherResultDTO;
import egovframework.example.user.dto.ReportDTO;
import egovframework.example.user.dto.TeachersDTO;
import egovframework.example.user.service.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserManageUtils {
    @Resource(name = "userManageService")
    private UserManageService userManageService;

    @Resource(name = "pollManageService")
    private PollManageService pollManageService;

    @Autowired
    @Qualifier(value = "naverServiceImpl")
    private NaverService naverService;

    /**
     * SEL 알기 - 교사 결과 DTO 생성 함수
     */
    public MySelTeacherResultDTO makeMySelTeacherResultDTO(List<MySelVO> voList, ArrayList<TeachersDTO> gneList) {
        ArrayList<ReportDTO> reportList = new ArrayList<>();
        ArrayList<TeachersDTO> infoArr = new ArrayList<>();

        for(int i=0; i<voList.size(); i++) {
            MySelVO vo = voList.get(i);
            HashMap<String, Integer> state = new HashMap<String, Integer>();

            for(int j=0; j<vo.getPollIdList().length; j++) {
                if(i == 0) {
                    reportList.add(ReportDTO.builder()
                            .pollNm(vo.getPollNmList()[j])
                            .startDate(vo.getStartDateList()[j])
                            .endDate(vo.getEndDateList()[j])
                            .expired(vo.getExpiredList()[j])
                            .build());
                }

                state.put(voList.get(i).getPollNmList()[j], Integer.parseInt(voList.get(i).getIsParticipateList()[j]));
            }

            infoArr.add(TeachersDTO.builder()
                    .userId(vo.getUserId())
                    .name(vo.getName())
                    .email(vo.getEmail())
                    .classInfo(vo.getClassInfo())
                    .sex(vo.getSex())
                    .stateList(state)
                    .stateFinList(makeStateFinMap(voList.get(i)))
                    .build());

            gneList = (ArrayList<TeachersDTO>) gneList.stream()
                    .filter(gne -> StringUtils.isNotEmpty(gne.getName()))
                    .filter(gne -> StringUtils.isNotEmpty(gne.getClassInfo()))
                    .filter(gne -> !(gne.getName().equals(vo.getName()) && gne.getClassInfo().equals(vo.getClassInfo())))
                    .collect(Collectors.toList());
        }

        infoArr.addAll(gneList);

        return MySelTeacherResultDTO.builder()
                .reportList(isEmptyReportList(reportList))
                .infoArr(infoArr)
                .build();
    }

    /**
     * reportList.isEmpty 일 떄, 에외처리 함수
     */
    public ArrayList<ReportDTO> isEmptyReportList(ArrayList<ReportDTO> reportList) {
        if(reportList.isEmpty()) {
            for(PollManageVO vo : pollManageService.selectReportsTeacher()) {
                reportList.add(ReportDTO.builder()
                        .pollNm(vo.getPollNm())
                        .startDate(vo.getStartDate())
                        .endDate(vo.getStartDate())
                        .expired(vo.getExpired() ? "1" : "0")
                        .build());
            }
        }

        return reportList;
    }

    /**
     * SEL 알기 - stateFin 생성 함수
     */
    public HashMap<String, String> makeStateFinMap(MySelVO vo) {
        HashMap<String, String> stateFinList = new HashMap<>();

        if(vo.getPollIDForfrstRegisterPnttm() != null && vo.getFrstRegisterPnttm() != null) {
            for(int i=0; i<vo.getPollIDForfrstRegisterPnttmList().length; i++) {

                stateFinList.put(vo.getPollIDForfrstRegisterPnttmList()[i], vo.getFrstRegisterPnttmList()[i]);
            }
        }

        return stateFinList;
    }

    /**
     * SEL 알기 - gne 학생 목록 생성 함수
     */
    public ArrayList<TeachersDTO> makeGneList(LoginVO header) {

        LoginVO loginVO = userManageService.selectUserInfo(header);

        GneUserDto gneUserDto = new GneUserDto();
        gneUserDto.setSchulCode(loginVO.getSchulCode());
        gneUserDto.setStGrade(loginVO.getGradeNm());
        gneUserDto.setStClass(loginVO.getClassCode());

        GneInfoDto<GneUserDto> gneInfoDto = new GneInfoDto<GneUserDto>();
        gneInfoDto.setData(gneUserDto);

        return (ArrayList<TeachersDTO>) naverService.procGneSchoolUserInfo(gneInfoDto).getData().stream()
                .map(dto -> TeachersDTO.builder()
                        .name(dto.getUserNm())
                        .email(dto.getStGrade() + "학년 " + dto.getStClass() + "반 " + dto.getStNumber() + "번")
                        .classInfo(dto.getStGrade() + "학년 " + dto.getStClass() + "반")
                        .build())
                .collect(Collectors.toList());
    }
}
