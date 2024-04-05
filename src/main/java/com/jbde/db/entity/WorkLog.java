package com.jbde.db.entity;

import java.util.Date;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Component
public class WorkLog {

	@Id
	public String empID;
	
	@Column(nullable = false)
	public String empName;
	
	@Column(nullable = false)
	public Date workDate;
	
	@Column(nullable = false)
	public String department;
	
	@Column(nullable = false)
	public String workedProductType;
	
	@Column(nullable = false)
	public String workedChannelName;
	
	@Column(nullable = false)
	public String workedProductCount;

}
