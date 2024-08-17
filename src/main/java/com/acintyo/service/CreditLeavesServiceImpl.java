package com.acintyo.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.acintyo.Entity.CreditLeaves;
import com.acintyo.Entity.TotalNumberOfLeaves;
import com.acintyo.customexceptions.EmployeeDataNotFound;
import com.acintyo.customexceptions.EmployeeLeaveDataNotFoundException;
import com.acintyo.dto.ApiResponse;
import com.acintyo.dto.CreditLeaveRequestDto;
import com.acintyo.dto.CreditLeavesResponseDto;
import com.acintyo.dto.TotalNumberOfLeavesDto;
import com.acintyo.repository.ICreditLeavesRepository;

@Service

public class CreditLeavesServiceImpl implements ICreditLeaveService {
	@Autowired
	private ICreditLeavesRepository repository;

	@Override
	public ApiResponse<?> getByEmpId(String employeeId) {
		Optional<CreditLeaves> optional = repository.findById(employeeId);
		if (optional.isPresent()) {
			CreditLeaves creditLeaves = optional.get();
			CreditLeavesResponseDto dto1 = new CreditLeavesResponseDto();
			dto1.setEmployeeId(creditLeaves.getEmployeeId());
			dto1.setCreditLeaves(creditLeaves.getCreditLeaves());
			dto1.setLeaveType(creditLeaves.getLeaveType());
			dto1.setMonth(creditLeaves.getMonth());
			return new ApiResponse<>(true, "Data fetched successfully", creditLeaves);
		}
		throw new EmployeeDataNotFound("Employee id not found with given id : " + employeeId);
	}

	@Override
	public ApiResponse<?> creditleavesdata(CreditLeaveRequestDto creditleave) {
		List<CreditLeaves> creditleaveslist = repository.findAll();
		List<CreditLeaves> updatedleaves = new ArrayList<>();
		for (CreditLeaves creditleaves : creditleaveslist) {
			if (LocalDateTime.now().getDayOfMonth() == 28) {
				creditleaves.setCreditLeaves(creditleaves.getCreditLeaves() + 1);
				creditleaves.setLeaveType("EarnedLeave");
				creditleaves.setMonth(LocalDate.now());
				updatedleaves.add(creditleaves);
				// updatedleaves=creditleaves.add(creditleaveslist);
				// List<CreditLeaves> creditleaveupdated=new ArrayList<>();
				repository.saveAll(updatedleaves);

			} else {
				throw new EmployeeLeaveDataNotFoundException("Employee Leave request Data Not Found");
			}

		}
		return new ApiResponse<>(true, "Based on month credit leaves data updated", updatedleaves);
	}

	@Override
	public ResponseEntity<?> totalNoOfLeaves(String empId) {
		List<CreditLeaves> totalNoOfLeaves=repository.findByEmployeeId(empId);
		double totalNoOfCompOff=0.0;
		double totalNoOfEarned=0.0;
		for(CreditLeaves creditleaves:totalNoOfLeaves) {
			if(creditleaves.getLeaveType().equalsIgnoreCase("CompOff")) {
				totalNoOfCompOff=totalNoOfCompOff+creditleaves.getCreditLeaves();
			}
			else {
				totalNoOfEarned=totalNoOfEarned+creditleaves.getCreditLeaves();
			}
		}
		double totalLeaves=totalNoOfCompOff+totalNoOfEarned;
		TotalNumberOfLeavesDto noOfLeaves=new TotalNumberOfLeavesDto();
		noOfLeaves.setTotalNoOfLeaves(totalLeaves);
		noOfLeaves.setEarnedLeaves(totalNoOfEarned);
		noOfLeaves.setCompOffLeaves(totalNoOfCompOff);
		return new ResponseEntity<>(new ApiResponse<>(true,"TotalNoOfLeaves",noOfLeaves),HttpStatus.OK);
	}
	

}
