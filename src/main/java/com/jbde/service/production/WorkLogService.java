package com.jbde.service.production;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jbde.db.entity.WorkLog;
import com.jbde.employee.JbdeWorkLog;
import com.jbde.repository.JbdeWorkLogRepo;
import com.jbde.ui.dto.JbdeWorkLogDTO;
import com.jbde.util.APIResponse;

@Service
public class WorkLogService {
	
	@Autowired
	JbdeWorkLogRepo jbdeWorkLogRepo;
	
	@Autowired
	WorkLog workLog;
	
	@Autowired
	APIResponse apiResponse;
	
	
	public APIResponse addDailyWorkService(JbdeWorkLogDTO workLogdto) {
		workLog.setEmpID(workLogdto.getEmpID());
		workLog.setEmpName(workLogdto.getEmpName());
		workLog.setDepartment(workLogdto.getDepartment());
		workLog.setWorkDate(workLogdto.getWorkDate());
		workLog.setWorkedChannelName(workLogdto.getWorkedChannelName());
		workLog.setWorkedProductType(workLogdto.getWorkedProductType());
		workLog.setWorkedProductCount(workLogdto.getWorkedProductCount());
		
		workLog = jbdeWorkLogRepo.save(workLog);
		System.out.println("WorkLogService :: updateDailyWorkService() : executed");
		apiResponse.setData(workLog);

		return apiResponse;
	}

	
}
