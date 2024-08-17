package com.acintyo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.acintyo.dto.ApiResponse;
import com.acintyo.dto.LeaveRequestDto;
import com.acintyo.service.ILeaveRequestService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/LeaveRequest")
@Validated

public class LeaveRequestController {
	
	@Autowired
	public ILeaveRequestService service;
	
	
	@PostMapping("/postingleave")
	public ResponseEntity<?> postleave(@RequestBody @Valid LeaveRequestDto dto){
		ApiResponse<?> leave=service.postingOfLeave(dto);
		return new ResponseEntity<>(leave,HttpStatus.OK);
	}	
	
	@PutMapping("/updateLeaveRequest-ById")
	public ResponseEntity<?> updateLeave(@RequestParam Integer Id, 
			@RequestBody @Valid LeaveRequestDto requestDto){
		ApiResponse<?> response=service.updateLeaveRequest(Id, requestDto);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	@PostMapping("/Comp-off-request")
	public ResponseEntity<?> compOffRequest(@RequestBody LeaveRequestDto dto){
		return service.compOffRequest(dto);
	}
	
	@PutMapping("/LeaveRequestApprovalByAdmin")
	public ResponseEntity<?> leaveRequestApproval(@RequestParam Integer id,@RequestParam String statusByAdmin){
		return new ResponseEntity<>(service.compOffApproval(id,statusByAdmin),HttpStatus.OK);
		
	}
}
	
