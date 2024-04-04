package com.jbde.entity;

import java.util.Date;

import lombok.Data;

@Data
public class JbdeDailyTimeLog {
	
	public String empName;
	public Date workDate;
	public String department;
	public String workedProductType;
	public String workedChannelName;
	public String workedProductCount;

	

}
