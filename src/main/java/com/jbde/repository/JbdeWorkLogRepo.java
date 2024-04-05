package com.jbde.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jbde.db.entity.WorkLog;

import java.util.List;
import java.util.Date;


public interface JbdeWorkLogRepo extends JpaRepository<WorkLog, Long> {

	List<WorkLog> findByEmpID(String empID);
	List<WorkLog> findByEmpName(String empName);
	List<WorkLog> findByDepartment(String department);
	List<WorkLog> findByWorkDate(Date workDate);
	List<WorkLog> findByWorkedChannelName(String workedChannelName);
	List<WorkLog> findByWorkedProductCount(String workedProductCount);
	List<WorkLog> findByWorkedProductType(String workedProductType);
}
