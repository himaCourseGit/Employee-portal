package com.acintyo.repository;

import java.util.List;
import java.util.Optional;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.repository.JpaRepository;

import com.acintyo.Entity.CreditLeaves;

public interface ICreditLeavesRepository extends JpaRepository<CreditLeaves,String> {
	//Optional<CreditLeaves> getByEmpId(String employeeId);
	List<CreditLeaves> findByEmployeeId(String empId);
	
	Optional<CreditLeaves> findByLeaveType(String leaveType);

	Optional<CreditLeaves> findByEmployeeIdAndLeaveType(String employeeId, String leaveType);
	
	
	

}
