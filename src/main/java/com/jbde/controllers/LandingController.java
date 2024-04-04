package com.jbde.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jbde.db.entity.Employee;
import com.jbde.db.entity.JbdeToken;
import com.jbde.repository.EmployeeListRepository;
import com.jbde.repository.JbdeTokenRepository;
import com.jbde.security.JbdeJwtToken;
import com.jbde.ui.dto.JbdeRequestMeta;

@Controller
@RequestMapping("/")
public class LandingController {

	@Autowired
	private EmployeeListRepository empllistrepo;

	@Autowired
	private JbdeRequestMeta jbdeRequestMeta;
	
	@Autowired
	private JbdeJwtToken jbdeJwtToken;
	
	@Autowired
	JbdeToken jbdeToken;
	
	@Autowired
	private JbdeTokenRepository jbdeTokenRepository;
	
	@RequestMapping(method = RequestMethod.GET, value = "/landing/{employee}")
	public String loadIndexPage(@PathVariable("employee") String empName, Model model) throws Exception {
		System.out.println("LandingController :: loadIndexPage() :: Emp ID: " + jbdeRequestMeta.getEmpID());
		String email = "test_22@test.com";
		jbdeToken = (JbdeToken) jbdeTokenRepository.findByJbdeToken(null);
		String auth = jbdeToken.getJbdeToken();
		jbdeJwtToken.validateJbdeToken(auth, email);
		List<Employee> employeeList = empllistrepo.findByEmpName(empName);
		model.addAttribute("employees", employeeList);

		return "landingPage";
	}

}
