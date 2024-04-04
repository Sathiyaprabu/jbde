package com.jbde.service.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jbde.db.entity.Employee;
import com.jbde.repository.EmployeeListRepository;
import com.jbde.util.APIResponse;

@Service
public class EmployeeService {

	@Autowired
	EmployeeListRepository employeeListRepository;

	@Autowired
	APIResponse apiResponse;
	
	public APIResponse registerEmployeesService(Employee empEntity) {

		//APIResponse apiResponse = new APIResponse();
		empEntity = employeeListRepository.save(empEntity);
		apiResponse.setData(empEntity);

		return apiResponse;
	}
	
	
	public APIResponse updateEmployeesDetails(Employee empEntity) {

		//APIResponse apiResponse = new APIResponse();
		empEntity = employeeListRepository.save(empEntity);
		apiResponse.setData(empEntity);

		return apiResponse;
	}
}
