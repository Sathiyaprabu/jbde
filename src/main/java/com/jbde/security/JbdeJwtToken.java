package com.jbde.security;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import org.springframework.stereotype.Service;

import com.jbde.db.entity.Employee;
import com.jbde.exception.JbdeAccessDeniedException;
import com.jbde.ui.dto.LoginRequestDTO;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ClaimsBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JbdeJwtToken {

	private static final String SECRET_KEY = "VGhpc19pc19KQkRFX1NlY3JldF9LZXlfVXNlZF9Gb3JfVGVzdGluZw==";
	private static final String SECRET_KEY_STRING = "This_is_JBDE_Secret_Key_Used_For_Testing";

	public static long milliSecTime = System.currentTimeMillis();
	public static long expiryDuration = 60 * 60;
	public static long rtexpiryDuration = expiryDuration * 2;

	Date issuedAt = new Date(milliSecTime);
	Date expireAt = new Date(milliSecTime + expiryDuration * 1000);
	Date rtissuedAt = new Date((milliSecTime + expiryDuration * 1000) - (120 * 1000));
	Date rtexpireAt = new Date(milliSecTime + rtexpiryDuration * 1000);

	public String generateJbeToken(Employee employee) {

		ClaimsBuilder claims = Jwts.claims().subject(employee.getEmpID()).issuer(employee.getEmpID()).issuedAt(issuedAt)
				.expiration(expireAt);

		claims.add("name", employee.getEmpName());
		claims.add("email", employee.getEmpEmail());
		claims.add("role", employee.getEmpRole());

		@SuppressWarnings("unchecked")
		String token = Jwts.builder().claims((Map<String, ?>) claims).signWith(getJbdeSigningKey()).compact();

		/*
		 * String token = Jwts.builder() .subject(employee.getID().toString())
		 * .issuer(employee.getEmpID()) .issuedAt(issuedAt) .expiration(expireAt)
		 * .signWith(getJbdeSigningKey()) .compact();
		 */

		return token;
	}

	public String generateJbdeRefreshToken(Employee employee) {

		/*
		 * String refreshtoken = Jwts.builder() .subject(employee.getID().toString())
		 * .issuer(employee.getEmpID()) .issuedAt(issuedAt) .expiration(expireAt)
		 * .signWith(getJbdeSigningKey()) .compact();
		 * 
		 * return refreshtoken;
		 */

		ClaimsBuilder claims = Jwts.claims().subject(employee.getEmpID()).issuer(employee.getEmpID())
				.issuedAt(rtissuedAt).expiration(rtexpireAt);

		claims.add("name", employee.getEmpName());
		claims.add("email", employee.getEmpEmail());
		claims.add("role", employee.getEmpRole());
		@SuppressWarnings("unchecked")
		String token = Jwts.builder().claims((Map<String, ?>) claims).signWith(getJbdeSigningKey()).compact();

		return token;
	}

	// verifying the Claims
	@SuppressWarnings("deprecation")
	public Claims verifyJbdeJwtToken(String authorization) throws Exception {
		System.out.println("JbdeJwtToken :: verifyJbdeJwtToken() ");

		try {

			Claims claims = Jwts.parser().setSigningKey(getJbdeSigningKey()).build().parseClaimsJws(authorization)
					.getBody();

			System.out.println("JbdeJwtToken :: verifyJbdeJwtToken() : Claims Details : Emp Name::  "
					+ claims.get("name") + "  Iss:: " + claims.getIssuer() + "\nClaim Body :: " + claims.toString());
			return claims;

		} catch (Exception e) {
			System.out.println("JbdeJwtToken :: verifyJbdeJwtToken() : In Catch Block");
			throw new JbdeAccessDeniedException("Access Denied");
		}

	}

	public boolean validateJbdeTokeninHeader(String authorization, String email) throws Exception {
		Claims claims = verifyJbdeJwtToken(authorization);
		System.out.println("Token - Email :: " + claims.get("email") + "\n" + "Login Email :: " + email + "\n");
		System.out.println("Is Email Valid :: " + ((claims.get("email").equals(email))));
		System.out.println("Is Token Valid :: " + !(claims.getExpiration().before(new Date())));
		return (claims.get("email").equals(email) && !(claims.getExpiration().before(new Date())));

	}

	public boolean validateJbdeToken(String token) throws Exception {
		// verify claim expiry
		try {
			Claims claims = extractAllClaims(token);
			System.out.println(
					"JbdeJwtToken :: Generated Claims: " + claims + "\nClaim Expiry : " + claims.getExpiration()
							+ "\nClaims getNotBefore : " + claims.getNotBefore() + "\nClaims Expiry & Before : "
							+ claims.getExpiration().before(new Date(System.currentTimeMillis())));

			// check the token expiry is after the current system date & time
			if (claims.getExpiration().after(new Date(System.currentTimeMillis()))) {
				System.out.println("JbdeJwtToken :: validateJbdeToken() : Token is valid ");
				return true;
			}
			
		} catch (Exception ex) {
			System.out.println("Exception Occurred while validating the claims : " + ex.getMessage());
			ex.getMessage();
		}

		System.out.println("JbdeJwtToken :: validateJbdeToken() : Token is EXPIRED ");
		return false;

	}

	public String extractEmployeeName(String token) {

		return extractJbdeClaim(token, Claims::getSubject);

	}

	private <T> T extractJbdeClaim(String token, Function<Claims, T> claimsResolvers) {
		final Claims claims = extractAllClaims(token);

		return claimsResolvers.apply(claims);

	}

	public Claims extractAllClaims(String token) {
		System.out.println("JbdeJwtToken :: extractAllClaims() ");
		return Jwts.parser().setSigningKey(getJbdeSigningKey()).build().parseClaimsJws(token).getBody();
	}

	private Key getJbdeSigningKey() {

		byte[] keyBytes = Decoders.BASE64URL.decode(SECRET_KEY);

		return Keys.hmacShaKeyFor(keyBytes);
	}
}
