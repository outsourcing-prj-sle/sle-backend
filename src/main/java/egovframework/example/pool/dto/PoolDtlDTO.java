package egovframework.example.pool.dto;

import java.util.ArrayList;
import java.util.HashMap;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class PoolDtlDTO {
	private String status;
	private String pollNm;
	private String startDate;
	private String endDate;
	private String step;
	private ArrayList<HashMap<String, Object>> metadata;
	private Boolean isSave;
	private Boolean isVoice;
}