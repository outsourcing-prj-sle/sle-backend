package egovframework.example.poll.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PollScoreDTO {
    private String authorization;
    private String qesitmAnswerType;
    private double poolScore;
}
