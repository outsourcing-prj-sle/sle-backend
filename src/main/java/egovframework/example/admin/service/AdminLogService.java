package egovframework.example.admin.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.example.admin.service.impl.AdminLogMapper;
import egovframework.example.cmmn.service.ApiLog;

@Service("adminLogService")
public class AdminLogService {

	@Resource(name = "adminLogMapper")
    private AdminLogMapper mapper;

    public void saveLog(ApiLog log) {
        mapper.insertLog(log);
    }
}