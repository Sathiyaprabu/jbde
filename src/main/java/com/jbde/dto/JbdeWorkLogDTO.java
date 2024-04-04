package com.jbde.dto;

import java.util.Date;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class JbdeWorkLogDTO {
	
	public String empName;
	public Date workDate;
	public String department;
	public String workedProductType;
	public String workedChannelName;
	public String workedProductCount;

	

}
