package com.acintyo.service;


import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.acintyo.dto.ApiResponse;
import com.acintyo.dto.CreditLeaveRequestDto;
import com.acintyo.dto.DebitLeaveRequestDto;
import com.acintyo.dto.LeaveRequestDto;

public interface ILeaveRequestService {
	public ApiResponse<?> postingOfLeave(LeaveRequestDto dto);
	public ApiResponse<?> findBYApproved(String status,Pageable page);
	//public ApiResponse<?> updateByApproved(Integer id,LeaveRequestDto dto);
	public ApiResponse<?> updateByApproved(Integer id,String approved,String employeeId,String approvedBy);
	public ApiResponse<?> updateLeaveRequest(Integer id, LeaveRequestDto dto);
	//ApiResponse<?> updateByApproved(Integer id, String approved, String employeeId, String approvedBy);
	ResponseEntity<?> compOffRequest(LeaveRequestDto dto);
	public ApiResponse<?> compOffApproval(Integer id, String statusByAdmin);
	//public ApiResponse<?> leaveRequestApprovalPreferrence(Integer id,Double requestDays,CreditLeaveRequestDto creditdto,DebitLeaveRequestDto debitdto,String employeeId );
}
