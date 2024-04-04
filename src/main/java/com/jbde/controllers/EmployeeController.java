package com.jbde.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.jbde.db.entity.Employee;
import com.jbde.service.employee.EmployeeService;
import com.jbde.util.APIResponse;

@Controller
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@PostMapping("/register")
	public ResponseEntity<APIResponse> registerNewEmployee(@RequestBody Employee empEntity, Model model) {
		System.out.println("registerNewEmployee Method Called");
		APIResponse apiResponse = employeeService.registerEmployeesService(empEntity);

		//model.addAttribute("register", apiResponse);

		//return "landingPage";

		return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
	}
}
