package com.jbde.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jbde.db.entity.JbdeToken;

public interface JbdeTokenRepository extends JpaRepository<JbdeToken, String> {

	List<JbdeToken> findByJbdeToken(String jbdeToken);
	

}
