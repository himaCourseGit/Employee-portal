package com.acintyo.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.acintyo.Entity.Employee;
import com.acintyo.Entity.EmployeeDirectory;

public interface IEmployeeDirectoryRepository  extends JpaRepository<EmployeeDirectory, Integer> {

	Optional<EmployeeDirectory>  findByEmployeeId(String employeeId);
	
	
	

    
    
	
	
	
}
