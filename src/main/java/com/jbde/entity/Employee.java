/**
 *
 */
package com.jbde.entity;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 *
 */
@Getter
@Setter
@Entity
@Data
@Component
@Table(name = "Employee")
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long ID;
	
	@Column(name="employeeID", length = 20, nullable = false, unique = true)
	public String empID;
	
	@Column(name = "employeeName", length = 40)
	public String empName;
	
	@Column(name = "empEmail", length = 50)
	public String empEmail;
	
	@Column(name = "empPassword", length = 50)
	public String empPassword;
	
	@Column(name = "empRole", length = 50)
	public String empRole;
	
	//public int empAge;
	//public int empExperience;
	//public StringBuilder empDepartment;

}
