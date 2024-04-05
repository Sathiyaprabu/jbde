/**
 *
 */
package com.jbde.generalpatterns;

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
public class JbdeTest {
	
	@Id
	public String empID;
	
	@Column(nullable = false)
	public String empName;
	
	@Column(nullable = false)
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
