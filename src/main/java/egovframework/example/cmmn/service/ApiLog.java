package egovframework.example.cmmn.service;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ApiLog {
    private Long logId;
    private String uniqId;
    private String id;
    private String userName;
    private String ipAddress;
    private String accessTime;
    private String apiUrl;
}