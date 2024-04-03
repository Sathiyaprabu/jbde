package com.jbde.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jbde.entity.Employee;

public interface EmployeeListRepository extends JpaRepository<Employee, Long> {

	List<Employee> findByEmpID(String emplID);

	List<Employee> findByEmpName(String empName);

	Optional<Employee > findOneByEmpEmailIgnoreCaseAndEmpPassword(String empEmail, String empPassword) throws Exception;

}
