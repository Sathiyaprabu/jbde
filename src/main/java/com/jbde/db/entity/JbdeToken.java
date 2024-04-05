package com.jbde.db.entity;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Component
@Entity
public class JbdeToken {

	@Id
	public String empID;
	
	@Column(nullable = false)
	String jbdeToken;
	
}
