package egovframework.example.poll.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.ArrayList;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PollMetaDTO {
    @JsonProperty("Q")
    private String Q;

    @JsonProperty("AT")
    private ArrayList<String> AT;

    @JsonProperty("AI")
    private ArrayList<String> AI;
}
