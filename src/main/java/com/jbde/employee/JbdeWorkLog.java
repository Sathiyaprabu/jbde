package com.jbde.employee;

import com.jbde.db.entity.WorkLog;

public interface JbdeWorkLog {

	public void updateDailyWork(WorkLog WorkLog);
	public void selectWorkType();
	public void loadEmployeeName();
	public Integer loadNumberOfWorkedProducts();
	
	
	
}
