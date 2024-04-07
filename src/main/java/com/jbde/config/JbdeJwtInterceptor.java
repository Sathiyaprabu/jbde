package com.jbde.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequestInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.WebRequestHandlerInterceptorAdapter;

import com.jbde.security.JbdeJwtToken;
import com.jbde.service.employee.EmployeeService;
import com.jbde.ui.dto.LoginRequestDTO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JbdeJwtInterceptor extends WebRequestHandlerInterceptorAdapter{

	@Autowired
	JbdeJwtToken jbdeJwtToken;
	
	@Autowired
	LoginRequestDTO loginRequestDTO;
	
	/*
	 * @Autowired EmployeeService empService;
	 */
	
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
		//System.out.println("Result of validateToken() : " + jbdeJwtToken.validateJbdeToken(auth));
		
		if(!(jbdeJwtToken.validateJbdeToken(auth))) {
			
			System.out.println("Please Login again");
			response.getWriter().write("Please Login again");
			return false;
		}
		
		System.out.println("JbdeJwtInterceptor :: preHandle() : NOT LOGIN/SIGNUP Check - after verifyJbdeJwtToken() called ");
	}
	
		return super.preHandle(request, response, handler);
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView model) throws Exception {
	
		System.out.println("JbdeJwtInterceptor :: postHandle() " + request.getAttributeNames());
		
		//empService.getAnEmployeesDetails(request.)
		//String empRole =  "ACTING"; // empService.checkEmployeeRoles(response.toString());
		/*
		 * if (empRole.equalsIgnoreCase("ACTING")) { // model.addAttribute("employees",
		 * empEntity); System.out.println("Logged in as Actor");
		 * 
		 * } else {
		 * 
		 * System.out.println("Not logged in from Acting Department"); }
		 */
		
	}
}
