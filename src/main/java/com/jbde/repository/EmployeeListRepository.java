package com.jbde.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jbde.entity.Employee;

public interface EmployeeListRepository extends JpaRepository<Employee, Long> {

	List<Employee> findByEmpID(String emplID);

	List<Employee> findByEmpName(String empName);

	Employee findOneByEmpEmailIgnoreCaseAndEmpPassword(String empEmail, String empPassword);

}
