package egovframework.example.admin.system.model;

import java.util.List;

import egovframework.example.cmmn.service.ApiLog;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IpLogListDTO {
	private List<ApiLog> logList;
    private int pageNo = 1;
    private int recordCount = 10;
    private int totalCount;
}
