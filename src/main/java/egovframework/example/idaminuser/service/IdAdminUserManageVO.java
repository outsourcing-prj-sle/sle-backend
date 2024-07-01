package egovframework.example.idaminuser.service;

import egovframework.example.cmmn.service.PaginationVO;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IdAdminUserManageVO extends PaginationVO {
    /**
     * 사용자 아이디
     */
    private String userId;

    /**
     * 사용자 구분 코드
     */
    private String userSeCode;

    /**
     * 비밀번호
     */
    private String password;

    /**
     * 사용자명
     */
    private String userNm;

    /**
     * 이메일 주소
     */
    private String emailAdres;

    /**
     * 전화번호
     */
    private String phoneNumber;

    /**
     * 사용자 소속 기관
     */
    private String userSpaceInfo;
}
