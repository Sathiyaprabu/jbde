package com.jbde.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.jbde.security.JbdeJwtToken;
import com.jbde.service.common.LoginService;
import com.jbde.ui.dto.LoginRequestDTO;
import com.jbde.ui.dto.SignUpRequestDTO;
import com.jbde.util.APIResponse;

@Controller
public class LoginController {

	@Autowired
	LoginService loginService;
	
	@Autowired
	APIResponse apiResponse;
	
	@Autowired
	JbdeJwtToken jbdeJwtToken;

	@PostMapping("/signup")
	public ResponseEntity<APIResponse> signUp(@RequestBody SignUpRequestDTO signupreqdto) {

		apiResponse = loginService.signUpService(signupreqdto);

		return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
	}

	@PostMapping("/login")
	public ResponseEntity<APIResponse> login(@RequestBody LoginRequestDTO loginreqdto) throws Exception {
		System.out.println("LoginController :: login() ");
		apiResponse = loginService.loginService(loginreqdto);

		return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
	}
	
	@GetMapping("/privateapi")
	public ResponseEntity<APIResponse> privateTestAPI(@RequestHeader(value = "authorization", defaultValue = "" ) String auth) throws Exception{
		
	//	jbdeJwtToken.verifyJbdeJwtToken(auth);
		System.out.println("LoginController :: privateTestAPI() ");
		apiResponse.setData("This is private api");
		return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
		
	}

}
