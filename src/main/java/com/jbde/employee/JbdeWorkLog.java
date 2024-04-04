package com.jbde.employee;

import com.jbde.db.entity.WorkLog;
import com.jbde.util.APIResponse;

public interface JbdeWorkLog {

	public APIResponse updateDailyWork(WorkLog WorkLog);
	public void selectWorkType();
	public void loadEmployeeName();
	public Integer loadNumberOfWorkedProducts();
	
	
	
}
