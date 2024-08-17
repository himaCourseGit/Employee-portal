package com.acintyo.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.acintyo.Entity.LeaveRequest;

public interface ILeaveRequestRepository extends JpaRepository<LeaveRequest, Integer> {
	
	public Page<LeaveRequest> findByStatusByAdmin(String status, Pageable page);
	
	@Modifying
	@Query(value = "UPDATE leave_request SET status_by_admin = :status and SET approved_by=:approvedBy WHERE ID = :id", nativeQuery = true)
	public void updateStatusByAdmin(Integer id, String status,String approvedBy);
	
	Optional<LeaveRequest> findByEmployeeIdAndIsActive(String empId ,String isActive);
	
	Optional<LeaveRequest> findById(Integer id);
	
	
	
	
	
}
