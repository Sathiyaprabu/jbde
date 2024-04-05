/**
 *
 */
package com.jbde.db.entity;

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

@Entity
@Data
@Component
@Table(name = "Employee")
public class Employee {
	
	@Id
	public String empID;
	
	@Column(nullable = false)
	public String empName;
	
	@Column(nullable = false, unique = true )
	public String empEmail;
	
	@Column(nullable = false)
	public String empPassword;
	
	@Column(nullable = false)
	public String empRole;
	
	@Column(nullable = false)
	public int empAge;
	
	@Column(nullable = false)
	public int empExperience;
	
	@Column(nullable = false)
	public String empDepartment;

}
