package egovframework.example.cmmn.service;

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
public class ApiLog extends PaginationVO{
    private Long logId;
    private String uniqId;
    private String id;
    private String userName;
    private String ipAddress;
    private String accessTime;
    private String apiUrl;
}