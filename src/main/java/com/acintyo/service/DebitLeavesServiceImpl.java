package com.acintyo.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acintyo.Entity.CreditLeaves;
import com.acintyo.Entity.DebitLeaves;
import com.acintyo.Entity.LeaveRequest;
import com.acintyo.customexceptions.EmployeeLeaveRequestDataNotFound;
import com.acintyo.dto.ApiResponse;
import com.acintyo.dto.DebitLeaveRequestDto;
import com.acintyo.dto.DebitLeavesResoponseDto;
import com.acintyo.repository.ICreditLeavesRepository;
import com.acintyo.repository.IDebitLeavesRepository;
import com.acintyo.repository.ILeaveRequestRepository;

@Service
public class DebitLeavesServiceImpl implements DebitLeaveService {
	@Autowired
	private IDebitLeavesRepository repository;
	@Autowired
	private ILeaveRequestRepository leaverepo;
	
	@Autowired
	private ICreditLeavesRepository creditLeavesRepository;

	@Override
	public ApiResponse<?> getByEmpId(String employeeId, DebitLeaveRequestDto dto) {
		Optional<DebitLeaves> optinal=repository.findById(employeeId);
		if(optinal.isPresent()) {
			DebitLeaves debitleaves=optinal.get();
			DebitLeavesResoponseDto response=new DebitLeavesResoponseDto();
		    response.setDebitLeaves(debitleaves.getDebitLeaves());
			response.setEmployeeId(debitleaves.getEmployeeId());
			response.setLeaveType(debitleaves.getLeaveType());
			response.setMonth(debitleaves.getMonth());
			return new ApiResponse<>(true,"Data Fetched successfully",response);
			
		}
		throw new EmployeeLeaveRequestDataNotFound("Employee debit leave request data not found");
	}

	public ApiResponse<?> updateByDebitLeaves(Integer id, String employeeId,Double requestdays) {
		Optional<LeaveRequest> leaverequest=leaverepo.findById(id);
		
		LeaveRequest leaveRequest2 = leaverequest.get();
		
		Optional<DebitLeaves> debitleaves=repository.findById(employeeId);
		if(debitleaves.isPresent()) {
			DebitLeaves debitLeaves=debitleaves.get();
			debitLeaves.setDebitLeaves(debitLeaves.getDebitLeaves()+requestdays);
			
			repository.save(debitLeaves);
		}
		
		DebitLeaves debitLeaves2=new DebitLeaves();
		debitLeaves2.setDebitLeaves(requestdays);
		debitLeaves2.setMonth(LocalDate.now());
		debitLeaves2=repository.save(debitLeaves2);
		
		Optional<CreditLeaves> optionalCreditLaves = creditLeavesRepository.findByEmployeeIdAndLeaveType(employeeId,leaveRequest2.getLeaveType());
		
		if(optionalCreditLaves.isPresent()) {
			
			CreditLeaves creditLeaves = optionalCreditLaves.get();
			creditLeaves.setCreditLeaves(creditLeaves.getCreditLeaves()-requestdays);
			creditLeaves.setLeaveType(leaveRequest2.getLeaveType());
			creditLeaves.setMonth(LocalDate.now());
			creditLeavesRepository.save(creditLeaves);
		}
		

		return new ApiResponse<>(true,"updated sucessfully debitleaves ",debitLeaves2);
	
		
	} 
	
	
	
	
	
	

}
