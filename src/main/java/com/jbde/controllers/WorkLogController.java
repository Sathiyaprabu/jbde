package com.jbde.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.jbde.service.production.WorkLogService;
import com.jbde.ui.dto.JbdeWorkLogDTO;
import com.jbde.util.APIResponse;

@Controller
public class WorkLogController {

	@Autowired
	WorkLogService workLogService;

	@Autowired
	APIResponse apiResponse;

	@PostMapping("/updatewl")
	public ResponseEntity<APIResponse> addDailyEmployeeWork(@RequestBody JbdeWorkLogDTO jbdeWorkLogDto) {
		System.out.println("WorkLogController :: updateDailyEmployeeWork()");
		apiResponse = workLogService.addDailyWorkService(jbdeWorkLogDto);

		return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
	}
}
