package egovframework.example.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClassPersonalityDTO {
    /**
     * [
     *     {
     *         "type1": {
     *             "values": [
     *                 3,
     *                 12
     *             ],
     *             "labels": [
     *                 "자주보고 점검하기",
     *                 "건너뛰며 점검하기"
     *             ]
     *         },
     *         "type2": {
     *             "values": [
     *                 2,
     *                 13
     *             ],
     *             "labels": [
     *                 "신속하게 과제하기",
     *                 "느긋하게 과제하기"
     *             ]
     *         },
     *         "type3": {
     *             "values": [
     *                 8,
     *                 7
     *             ],
     *             "labels": [
     *                 "소통하며 학습하기",
     *                 "독립적으로 학습하기"
     *             ]
     *         }
     *     }
     * ]
     */
    private ClassPersonalityDtlDTO type1;
    private ClassPersonalityDtlDTO type2;
    private ClassPersonalityDtlDTO type3;
}
