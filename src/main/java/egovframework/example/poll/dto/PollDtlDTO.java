package egovframework.example.poll.dto;

import java.util.ArrayList;
import java.util.HashMap;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PollDtlDTO {
	private String status;
	private String pollNm;
	private String startDate;
	private String endDate;
	private String step;
	private ArrayList<PollMetaDTO> metadata;
	private Boolean isSave;
	private Boolean isVoice;
}