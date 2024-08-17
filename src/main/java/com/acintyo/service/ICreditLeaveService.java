package com.acintyo.service;



import org.springframework.http.ResponseEntity;

import com.acintyo.dto.ApiResponse;
import com.acintyo.dto.CreditLeaveRequestDto;

public interface ICreditLeaveService {
	
	public ResponseEntity<?> totalNoOfLeaves(String empId);
	public ApiResponse<?> getByEmpId(String employeeId);
	public ApiResponse<?> creditleavesdata(CreditLeaveRequestDto creditleave);

}
