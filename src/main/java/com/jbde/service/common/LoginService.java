package com.jbde.service.common;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import com.jbde.db.entity.Employee;
import com.jbde.db.entity.JbdeToken;
import com.jbde.repository.EmployeeListRepository;
import com.jbde.repository.JbdeTokenRepository;
import com.jbde.security.JbdeJwtToken;
import com.jbde.service.employee.EmployeeService;
import com.jbde.ui.dto.LoginRequestDTO;
import com.jbde.ui.dto.SignUpRequestDTO;
import com.jbde.util.APIResponse;
import io.jsonwebtoken.Claims;

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
		System.out.println("LoginService :: signUpService() ");
		// dtp to entity convert
		// Employee empEntity = new Employee();
		// empEntity
		empEntity.setEmpEmail(signupdto.getSuempEmail());
		empEntity.setEmpID(signupdto.getSuempID());
		empEntity.setEmpName(signupdto.getSuempName());
		empEntity.setEmpPassword(signupdto.getSuempPassword());
		empEntity.setEmpRole(signupdto.getSuempRole());
		empEntity.setEmpAge(signupdto.getSuempAge());
		empEntity.setEmpExperience(signupdto.getSuempExperience());
		empEntity.setEmpDepartment(signupdto.getSuempDepartment());

		empEntity = employeeListRepository.save(empEntity);

		String token = jbdeJwtToken.generateJbeToken(empEntity);
		Map<String, Object> data = new HashMap<>();
		data.put("accessToken", token);

		apiResponse.setData(data);

		return apiResponse;
	}

	public APIResponse loginService(LoginRequestDTO logindto) throws Exception {
		
		// verify login details
		System.out.println("LoginService :: loginService() ");
		System.out.println("LoginService :: loginService() : User Details: " + logindto.getEmpLoginEmail()
				+ "\nPassword: " + logindto.getEmpLoginPassword());

		System.out.println("---------------------------");
		Optional<Employee> empReturn = employeeListRepository
				.findOneByEmpEmailIgnoreCaseAndEmpPassword(logindto.getEmpLoginEmail(), logindto.getEmpLoginPassword());
		if (empReturn.isPresent()) {
			empEntity = empReturn.get();
			System.out.println("LoginService :: loginService() : Optional Check : PASSED :Employe Details : \n"
					+ empEntity.toString());

			String token = jbdeJwtToken.generateJbeToken(empReturn.get());
			System.out.println("LoginService :: loginService() : Token Generated ");

			// Adding Token in DB
			jbdeToken.setEmpID(empEntity.getEmpID());
			jbdeToken.setJbdeToken(token);
			jbdeTokenRepository.save(jbdeToken);

			Map<String, Object> data = new HashMap<>();
			data.put("accessToken", token);
			token = jbdeJwtToken.generateJbdeRefreshToken(empReturn.get());
			data.put("refreshToken", token);
			apiResponse.setData(data);
			// verify claim expiry
			Claims claims = jbdeJwtToken.extractAllClaims(token);
			System.out.println(
					"LoginService :: Generated Claims: " + claims + "\nClaim Expiry : " + claims.getExpiration()
							+ "\nClaims getNotBefore : " + claims.getNotBefore() + "\nClaims Expiry & Before : "
							+ claims.getExpiration().before(new Date(System.currentTimeMillis())));

			// check the token expiry is after the current system date & time
			 

		} else {

			apiResponse.setData("User Login Failed");
			// apiResponse.setError(401);
			System.out.println("User Login Failed ... ");
			//return apiResponse;
		}

		

		// response
		/*
		 * if (empEntity == null) { apiResponse.setData("User Login Failed"); } else {
		 * System.out.println("Login Successfull.."); }
		 */

		return apiResponse;
	}

	public void fetchEmployeeDetailsForJwt(LoginRequestDTO logindto) throws Exception {

		Optional<Employee> empReturn = employeeListRepository
				.findOneByEmpEmailIgnoreCaseAndEmpPassword(logindto.getEmpLoginEmail(), logindto.getEmpLoginPassword());

	}
}
