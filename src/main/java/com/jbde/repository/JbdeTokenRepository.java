package com.jbde.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jbde.entity.JbdeToken;

public interface JbdeTokenRepository extends JpaRepository<JbdeToken, Long> {

	List<JbdeToken> findByJbdeToken(String jbdeToken);
	

}
