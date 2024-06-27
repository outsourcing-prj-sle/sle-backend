package egovframework.example.naver.controller;

import egovframework.example.cmmn.service.LoginVO;
import egovframework.example.naver.dto.*;
import egovframework.example.naver.service.NaverService;
import egovframework.example.user.service.UserManageService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @Class Name : NaverController.java
 * @Description : 웨일 스페이스 연동 및 경남교육청 연동 관련
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2024.06.21  SON         최초생성
 */
@Controller
public class NaverController {

    @Autowired
    @Qualifier(value = "naverServiceImpl")
    private NaverService naverService;

    @Resource(name = "userManageService")
    private UserManageService userManageService;

    /**
     * 네이버 억세스토큰 발급 API
     * @param naverCodeDto
     * @return
     */
    @Deprecated
    @ResponseBody
    @RequestMapping(value = "/api/naver/oauth2/token", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity postApiNaverOauth2Code(@RequestBody NaverCodeDto naverCodeDto) {

        // 네이버 인증코드로 억세스토큰 발급
        NaverTokenDto naverTokenDto = naverService.procAccessToken(naverCodeDto);

        // 네이버 억세스토큰으로 사용자정보 조회
        NaverUserDto naverUserDto = naverService.procUserInfo(naverTokenDto);

        // 네이버 사용자정보로 경남교육청 사용자정보 조회
        GneInfoDto<GneUserDto> gneUserDto = naverService.procGneUserInfo(naverUserDto);
        
        if(!gneUserDto.isSuccess()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("경남교육청 사용자 정보를 가져오는데 실패하였습니다.");
        }

        if(StringUtils.isEmpty(gneUserDto.getData().getUserId())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("사용자 아이디가 없습니다.");
        }

        LoginVO loginVO = LoginVO.builder()
                .authorization(gneUserDto.getData().getUserId())
                .name(gneUserDto.getData().getUserNm())
                .userEmail(naverUserDto.getPrimaryEmail())
                .profileImageId(naverUserDto.getThumbnailPhotoUrl())
                .schulGradeCode(gneUserDto.getData().getSchulGradeCode())
                .stYear(gneUserDto.getData().getStdrYear())
                .gradeNm(gneUserDto.getData().getStGrade())
                .classCode(gneUserDto.getData().getStClass())
                .classNm(gneUserDto.getData().getStClassNm())
                .stNumber(gneUserDto.getData().getStNumber())
                .userType(gneUserDto.getData().getUserSeCode())
                .userSpaceInfo(gneUserDto.getData().getSchulNm())
                .schulCode(gneUserDto.getData().getSchulCode())
                .schulGradeCode(gneUserDto.getData().getSchulGradeCode())
                .build();

        try {
            URI redirectUri = new URI("/naver/callback");

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setLocation(redirectUri);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity.ok(userManageService.insertUserInfo(loginVO));
    }
}