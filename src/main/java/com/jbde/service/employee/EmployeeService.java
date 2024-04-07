package com.jbde.service.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jbde.constants.Departments;
import com.jbde.db.entity.Employee;
import com.jbde.repository.EmployeeListRepository;
import com.jbde.util.APIResponse;

@Service
public class EmployeeService {
	
	Departments dept;
	
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
	
	public APIResponse getAnEmployeesDetails(Employee empEntity) {

		//APIResponse apiResponse = new APIResponse();
		//empEntity = employeeListRepository.findByEmployee(empEntity.getEmpID());
		System.out.println("An Employee Record : " + empEntity);
		apiResponse.setData(empEntity);

		return apiResponse;
	}
	
	public String checkEmployeeRoles(Employee employee) {
		
		if(employee.getEmpDepartment().equalsIgnoreCase(dept.ACTING.toString())) {
			System.out.println("My Dept is : " + dept.ACTING.toString());
			return dept.ACTING.toString();
		}
		
		return "Department No Assigned";
	}
}
