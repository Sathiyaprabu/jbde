package com.jbde.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.jbde.config.JbdeJwtInterceptor;
import com.jbde.dto.JbdeRequestMeta;

@Configuration
public class JbdeCustomWebConfig extends WebMvcConfigurationSupport {

	@Autowired
	private JbdeJwtInterceptor jwtInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		registry.addInterceptor(jwtInterceptor);
	}

	@Bean
	@RequestScope
	public JbdeRequestMeta getJbdeRequestMeta() {

		return new JbdeRequestMeta();
	}

	/*
	 * @Bean public JbdeJwtInterceptor jbdeJwtInterceptor() {
	 * 
	 * return new JbdeJwtInterceptor(getJbdeRequestMeta()); }
	 */
}
