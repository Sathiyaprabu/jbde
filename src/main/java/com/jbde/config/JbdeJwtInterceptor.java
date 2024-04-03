package com.jbde.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequestInterceptor;
import org.springframework.web.servlet.handler.WebRequestHandlerInterceptorAdapter;

import com.jbde.security.JbdeJwtToken;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JbdeJwtInterceptor extends WebRequestHandlerInterceptorAdapter{

	@Autowired
	JbdeJwtToken jbdeJwtToken;
	
	public JbdeJwtInterceptor(WebRequestInterceptor requestInterceptor) {
		super(requestInterceptor);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
	
		System.out.println("JbdeJwtInterceptor :: preHandle() : URI -  "+ request.getRequestURI());
		
		String auth = request.getHeader("authorization");
		System.out.println("JbdeJwtInterceptor :: preHandle() : Auth in Header - " + auth);
		
	if(!(request.getRequestURI().contains("login") || request.getRequestURI().contains("signup"))) {
		jbdeJwtToken.verifyJbdeJwtToken(auth);
		System.out.println("JbdeJwtInterceptor :: preHandle() : NOT LOGIN/SIGNUP Check - after verifyJbdeJwtToken() called ");
	}
	
		return super.preHandle(request, response, handler);
	}
}
