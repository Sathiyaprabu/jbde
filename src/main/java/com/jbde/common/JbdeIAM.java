package com.jbde.common;

import com.jbde.entity.Employee;
import com.jbde.security.JbdeJwtToken;

public class JbdeIAM {

	public boolean checkByEmployeeRole(Employee employee, JbdeJwtToken jbdeJwtToken) {

		return true;
	}

	public boolean checkByEmployeeId(Employee employee, JbdeJwtToken jbdeJwtToken) {

		return true;
	}

	public boolean checkByEmployeeRoleAndId(Employee employee, JbdeJwtToken jbdeJwtToken) {

		return true;
	}

}
