package com.jbde.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jbde.dto.JbdeRequestMeta;
import com.jbde.entity.Employee;
import com.jbde.repository.EmployeeListRepository;

@Controller
@RequestMapping("/")
public class LandingController {

	@Autowired
	private EmployeeListRepository empllistrepo;

	@Autowired
	private JbdeRequestMeta jbdeRequestMeta;
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/landing/{employee}")
	public String loadIndexPage(@PathVariable("employee") String empName, Model model) {
		System.out.println("Inside IndexMapping Method with Req META::" + jbdeRequestMeta.getEmpID());

		List<Employee> employeeList = empllistrepo.findByEmpName(empName);
		model.addAttribute("employees", employeeList);

		return "landingPage";
	}

}
