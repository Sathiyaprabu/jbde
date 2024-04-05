package com.jbde.util;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.jbde.db.entity.Employee;

import io.jsonwebtoken.ClaimsBuilder;
import io.jsonwebtoken.Jwts;

@Component
public class JbdeJwtUtil {

	private static String jbdesecret = "Jbd-Enter-prises-org";
	
	public String generateJbdeJwt(Employee empentity) {
		
		Date issuesAt = new Date();
		
		//claims
		ClaimsBuilder jbdeclaims = Jwts.claims()
				.setIssuer(empentity.getEmpID().toString())
				.setIssuedAt(issuesAt);
				
		return "";
	}
}
