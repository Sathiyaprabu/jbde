package com.jbde.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.jbde.util.APIResponse;

@ControllerAdvice
@Service 
public class JbdeGlobalExceptionHandler {

	@Autowired
	APIResponse apiResponse;
	
	
	@ExceptionHandler
	public ResponseEntity handleAccessDeniedException(JbdeAccessDeniedException e) {
		
		apiResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
				
		return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
	}
}
