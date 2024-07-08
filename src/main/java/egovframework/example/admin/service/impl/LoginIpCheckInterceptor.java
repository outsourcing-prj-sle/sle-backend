package egovframework.example.admin.service.impl;

import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import com.fasterxml.jackson.databind.ObjectMapper;

import egovframework.example.admin.service.AdminLogService;
import egovframework.example.admin.service.AdminManageService;
import egovframework.example.admin.system.service.AdminSystemManageService;
import lombok.extern.slf4j.Slf4j;
import egovframework.example.cmmn.service.AccountDTO;

import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class LoginIpCheckInterceptor implements HandlerInterceptor {

    @Resource(name = "adminLogService")
    private AdminLogService AdminLogService;
    
    @Resource(name = "adminManageService")
    private AdminManageService adminManageService;
    
    @Resource(name = "adminSystemManageService")
    private AdminSystemManageService adminSystemService;
    
    private ObjectMapper objectMapper =  new ObjectMapper();
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    	String remoteIp = request.getHeader("X-Forwarded-For");
    	log.info(remoteIp);
    	ServletInputStream inputStream = request.getInputStream();
    	String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
    	AccountDTO msg = objectMapper.readValue(messageBody, AccountDTO.class);
    	List<String> allowedIp = adminSystemService.selectIpByUserId(msg.getId());
    	request.setAttribute("requestBody", msg);
    	if(allowedIp.contains("0.0.0.0")) {
    		return true;
    	}
    	if(allowedIp == null || !allowedIp.contains(remoteIp)) {
	        response.sendError(HttpServletResponse.SC_OK, "허용 되지 않은 IP 입니다.");
	        return false;
    	}
        return true;
    }
}