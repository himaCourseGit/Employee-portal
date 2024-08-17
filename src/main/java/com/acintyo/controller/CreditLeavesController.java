package com.acintyo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.acintyo.dto.ApiResponse;
import com.acintyo.dto.CreditLeaveRequestDto;
import com.acintyo.service.ICreditLeaveService;

@RestController
@RequestMapping("/CreditLeaves")
public class CreditLeavesController {
	@Autowired
	public ICreditLeaveService service;
	
	@GetMapping("/getByEmpId")
	public ResponseEntity<?> getEmpById(@RequestParam String employeeId){
		ApiResponse<?> credit=service.getByEmpId(employeeId);
		return new ResponseEntity<>(credit,HttpStatus.OK);	
	}
	@PutMapping("/UpdatingCreditLeaves")
	public ResponseEntity<?> updateCreditLeavesdata(@RequestBody CreditLeaveRequestDto dto){
		ApiResponse<?> update=service.creditleavesdata(dto);
		return new ResponseEntity<>(update,HttpStatus.OK);
		
	}
	
	
	
	
	
	

}
