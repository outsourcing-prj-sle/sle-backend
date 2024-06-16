package egovframework.example.pool.service;

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
public class PoolDtl {
	private String status;
	private String pollNm;
	private String startDate;
	private String endDate;
	private ArrayList<String> step;
	private ArrayList<HashMap<String, Object>> metadata;
	private Boolean isSave;
	private Boolean isVoice;
}