package com.jbde.employee;

import com.jbde.ui.dto.JbdeWorkLogDTO;
import com.jbde.util.APIResponse;

public interface JbdeWorkLog {

	public APIResponse updateDailyWorkService(JbdeWorkLogDTO WorkLogdto);
	public void selectWorkType();
	public void loadEmployeeName();
	public Integer loadNumberOfWorkedProducts();
	
	
	
}
