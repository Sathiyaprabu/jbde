package com.jbde.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jbde.dto.LoginRequestDTO;
import com.jbde.dto.SignUpRequestDTO;
import com.jbde.entity.Employee;
import com.jbde.repository.EmployeeListRepository;
import com.jbde.security.JbdeJwtToken;
import com.jbde.util.APIResponse;

@Service
public class LoginService {

	@Autowired
	EmployeeListRepository employeeListRepository;

	@Autowired
	APIResponse apiResponse;
	
	@Autowired
	JbdeJwtToken jbdeJwtToken;
	
	

	public APIResponse signUpService(SignUpRequestDTO signupdto) {

		// validation
		System.out.println("Inside SignUp method...");
		// dtp to entity convert
		Employee empEntity = new Employee();
		empEntity.setEmpEmail(signupdto.getSuempEmail());
		empEntity.setEmpID(signupdto.getSuempID());
		empEntity.setEmpName(signupdto.getSuempName());
		empEntity.setEmpPassword(signupdto.getSuempPassword());
		empEntity.setEmpRole(signupdto.getSuempRole());

		empEntity = employeeListRepository.save(empEntity);
		String token = jbdeJwtToken.generateJbeToken(empEntity);
		Map<String, Object> data = new HashMap<>();
		data.put("accessToken", token);
		
		apiResponse.setData(data);

		return apiResponse;
	}

	public APIResponse loginService(LoginRequestDTO logindto) {

		// validation

		// verify login details
		System.out.println("User Details: " + logindto.getEmpLoginEmail() + " and " + logindto.getEmpLoginPassword());
		Employee emplogin = employeeListRepository
				.findOneByEmpEmailIgnoreCaseAndEmpPassword(logindto.getEmpLoginEmail(), logindto.getEmpLoginPassword());

		// response
		if (emplogin == null) {
			apiResponse.setData("User Login Failed");
		} 
		
		String token = jbdeJwtToken.generateJbeToken(emplogin);
		Map<String, Object> data = new HashMap<>();
		data.put("accessToken", token);
		
		apiResponse.setData(data);

		return apiResponse;
	}

}
