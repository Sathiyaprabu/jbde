package com.jbde.ui.dto;

import lombok.Data;


@Data
public class SignUpRequestDTO {
	
	private String suempID;
	private String suempName;
	private String suempEmail;
	private String suempPassword;
	private String suempRole;
	public int suempAge;
	public int suempExperience;
	public String suempDepartment;
	
}
