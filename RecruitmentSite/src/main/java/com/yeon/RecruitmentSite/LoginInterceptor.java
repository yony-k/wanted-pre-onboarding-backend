package com.yeon.RecruitmentSite;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

//특정페이지는 로그인한 유저만 사용할 수 있도록 하는 인터셉터
@Component
public class LoginInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		boolean isAjaxRequest = "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
	    HttpSession session = request.getSession(false);

	    if (session != null && session.getAttribute("user") != null) {
	        return true;
	    } else {
	        if (isAjaxRequest) {
	            response.setContentType("application/json;charset=UTF-8");
	            response.getWriter().write("{\"success\": false, \"message\": \"로그인이 필요합니다.\"}");
	            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401 Unauthorized
	        } else {
	            response.sendRedirect("/login/form");
	        }
	        return false;
	    }
	}
	
	

}
