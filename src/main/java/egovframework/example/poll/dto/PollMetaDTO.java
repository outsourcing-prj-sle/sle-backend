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
    private String Q;

    private ArrayList<String> AT;

    private ArrayList<String> AI;
}
