/**
 *
 */
package com.jbde.dto;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 *
 */
@Data
@Component
public class LoginRequestDTO {
	
	public String empLoginEmail;
	public String empLoginPassword;
	
	//public int empAge;
	//public int empExperience;
	//public StringBuilder empDepartment;

}
