package com.acintyo.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

//import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.acintyo.Entity.Employee;
import com.acintyo.dto.ApiResponse;
//import com.acintyo.dto.ApiResponse;
@Repository
public interface IEmployeeRepository extends JpaRepository<Employee,Long> {
	//security
	
	    Page<Employee> findByIsApproved(String isApproval,Pageable pageable);
	    
		Optional<Employee> findByEmail(String email);
		
		Optional<Employee> findByEmployeeIdAndIsActive(String empId,String isActive);
		
		Optional<Employee> findByAadharnumberAndEmail(Long aadharNumber,String email);
		
		Optional<Employee> findByAadharnumber(Long aadharNumber);
		
		Optional<Employee> findByEmployeeIdAndPassword(String empId,String password);
		
		Optional<Employee> findByEmailAndPassword(String email,String password);
		
		Optional<Employee> findByEmployeeId(String employeeId);
		

}
