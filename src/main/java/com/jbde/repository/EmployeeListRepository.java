package com.jbde.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jbde.db.entity.Employee;

public interface EmployeeListRepository extends JpaRepository<Employee, String> {

	List<Employee> findByEmpID(String emplID);
	
	//Employee findByEmployee(String emplID);

	List<Employee> findByEmpName(String empName);

	Optional<Employee > findOneByEmpEmailIgnoreCaseAndEmpPassword(String empEmail, String empPassword) throws Exception;

}
