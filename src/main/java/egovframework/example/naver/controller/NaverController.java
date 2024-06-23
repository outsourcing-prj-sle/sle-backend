package egovframework.example.naver.controller;

import egovframework.example.naver.dto.*;
import egovframework.example.naver.service.NaverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
        
        // 네이버 사용자정보로 경남교육청 학교별 사용자 목록 조회
        GneListDto<GneSchoolUserDto> gneSchoolUserDto = naverService.procGneSchoolUserInfo(gneUserDto);

        return ResponseEntity.ok(null);
    }
    
}