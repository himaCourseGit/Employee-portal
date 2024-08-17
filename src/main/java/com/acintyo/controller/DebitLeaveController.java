package com.acintyo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.acintyo.dto.ApiResponse;
import com.acintyo.dto.DebitLeaveRequestDto;
import com.acintyo.service.DebitLeaveService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/DebitLeaves")
public class DebitLeaveController {
	@Autowired
	private DebitLeaveService debitleaveservice;
	
	@GetMapping("GetDebitLeavesBy-Empid")
	public ResponseEntity<?> getByEmpId(@RequestParam String employeeId,  @RequestBody DebitLeaveRequestDto dto)
	{
		ApiResponse<?> debit=debitleaveservice.getByEmpId(employeeId, dto);
		return new ResponseEntity<>(debit,HttpStatus.OK);	
	}
	@PutMapping("/updatingDebitleaves")
	public ResponseEntity<?> updateByDebitLeaves(@RequestParam Integer id, @RequestParam String employeeId,@RequestParam Double requestdays) {
		ApiResponse<?> update=debitleaveservice.updateByDebitLeaves(id, employeeId, requestdays);
		return new ResponseEntity<>(update,HttpStatus.OK);
	}
		
	

}
