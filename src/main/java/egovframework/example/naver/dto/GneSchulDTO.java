package egovframework.example.naver.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GneSchulDTO {
    private String schulCode;  // 학교코드
    private String schulNm;   // 학교명
}
