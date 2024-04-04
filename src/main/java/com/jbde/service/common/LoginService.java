package com.jbde.service.common;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jbde.db.entity.Employee;
import com.jbde.db.entity.JbdeToken;
import com.jbde.repository.EmployeeListRepository;
import com.jbde.repository.JbdeTokenRepository;
import com.jbde.security.JbdeJwtToken;
import com.jbde.ui.dto.LoginRequestDTO;
import com.jbde.ui.dto.SignUpRequestDTO;
import com.jbde.util.APIResponse;

@Service
public class LoginService {

	@Autowired
	EmployeeListRepository employeeListRepository;

	@Autowired
	APIResponse apiResponse;
	
	@Autowired
	JbdeJwtToken jbdeJwtToken;
	
	@Autowired
	Employee empEntity;
	
	@Autowired
	JbdeTokenRepository jbdeTokenRepository;
	
	@Autowired
	JbdeToken jbdeToken;

	public APIResponse signUpService(SignUpRequestDTO signupdto) {

		// validation
		System.out.println("Inside SignUp method...");
		// dtp to entity convert
		//Employee empEntity = new Employee();
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

	public APIResponse loginService(LoginRequestDTO logindto) throws Exception {

		// validation

		// verify login details
		System.out.println("In Login Service Class & inside loginService() method...");
		System.out.println("User Details: " + logindto.getEmpLoginEmail() + " and " + logindto.getEmpLoginPassword());
		
			System.out.println("---------------------------");
			Optional <Employee> empReturn = employeeListRepository
					.findOneByEmpEmailIgnoreCaseAndEmpPassword(logindto.getEmpLoginEmail(), logindto.getEmpLoginPassword());
			if(empReturn.isPresent()) {
				System.out.println("Optional Checked... :: " + empReturn.toString());
				String token = jbdeJwtToken.generateJbeToken(empReturn.get());
				jbdeToken.setJbdeToken(token);
				jbdeTokenRepository.save(jbdeToken);
				Map<String, Object> data = new HashMap<>();
				data.put("accessToken", token);
				token = jbdeJwtToken.generateJbdeRefreshToken(empReturn.get());
				data.put("refreshToken", token);
				apiResponse.setData(data);
				
			} else {
				
				apiResponse.setData("User Login Failed");
				apiResponse.setError(401);
				System.out.println("User Login Failed ... ");
				return apiResponse;
			}
			
			System.out.println("ID from Query::  " + empReturn.get().getID());
	
		
		// response
		/*
		 * if (empEntity == null) { apiResponse.setData("User Login Failed"); } else {
		 * System.out.println("Login Successfull.."); }
		 */	

		return apiResponse;
	}

}
