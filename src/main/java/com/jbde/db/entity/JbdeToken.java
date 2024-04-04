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
@Table(name = "JbdeToken")
public class JbdeToken {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long ID;
	
	@Column(name="jbdeToken", nullable = false)
	String jbdeToken;
	
}
