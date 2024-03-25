package com.jbde.util;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class APIResponse {
	
	
	//private Long ID;
	private Integer status;
	private Object data;
	private Object error;

	public APIResponse() {
		this.status = HttpStatus.OK.value();
		this.data = data;
		this.error = error;
	}

}
