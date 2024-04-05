package com.jbde.generalpatterns;


import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Data
@Entity
@Component
//@Table(name = "JBDEEntity")
public class JBDEEntity {

	@Id
	public String empId;
	
	}
