package egovframework.example.admin.service.impl;

import org.apache.ibatis.annotations.Insert;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import egovframework.example.cmmn.service.ApiLog;

@Mapper("adminLogMapper")
public interface AdminLogMapper {
    @Insert("INSERT INTO ADMIN_LOG (USER_SE_CODE, USER_ID, USER_NAME, IP_ADDRESS, ACCESS_TIME, API_URL) VALUES (#{id}, #{uniqId}, #{userName}, #{ipAddress}, now(), #{apiUrl})")
    void insertLog(ApiLog log);
}