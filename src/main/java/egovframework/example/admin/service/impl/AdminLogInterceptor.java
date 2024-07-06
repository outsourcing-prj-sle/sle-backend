package egovframework.example.admin.service.impl;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import egovframework.example.admin.service.AdminLogService;
import egovframework.example.admin.service.AdminManageService;
import egovframework.example.cmmn.service.ApiLog;
import egovframework.example.cmmn.service.AdminLoginVO;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AdminLogInterceptor implements HandlerInterceptor {

    @Resource(name = "adminLogService")
    private AdminLogService AdminLogService;
    
    @Resource(name = "adminManageService")
    private AdminManageService adminManageService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        AdminLoginVO adminLoginVO = AdminLoginVO.builder()
        				.uniqId(request.getHeader("authorization"))	
        				.build();
        if(!adminManageService.authorizationUser(adminLoginVO.getUniqId())) {
            response.setStatus(HttpServletResponse.SC_OK); // 200 OK
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("{\"message\": \"Authorization Failed\"}");
        	return false;
        }
        adminLoginVO = adminManageService.selectUser(adminLoginVO);
        String ipAddress = request.getRemoteAddr();
        String apiUrl = request.getRequestURI();
        
        ApiLog log = ApiLog.builder()
	        		.uniqId(adminLoginVO.getUniqId())
	        		.id(adminLoginVO.getId())
	        		.userName(adminLoginVO.getName())
	        		.ipAddress(ipAddress)
	        		.apiUrl(apiUrl)
	        		.build();

        AdminLogService.saveLog(log);
        return true;
    }
}