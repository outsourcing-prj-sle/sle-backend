package egovframework.example.poll.dto;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;

@Data
@Builder
public class PollMetaDTO {
    private String Q;
    private ArrayList<String> AT;
    private ArrayList<String> AI;
}
