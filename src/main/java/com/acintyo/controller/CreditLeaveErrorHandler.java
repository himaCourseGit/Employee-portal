package com.acintyo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.acintyo.customexceptions.EmployeeDataNotFound;
import com.acintyo.customexceptions.EmployeeLeaveDataNotFoundException;

@RestControllerAdvice
public class CreditLeaveErrorHandler {
	@ExceptionHandler(EmployeeDataNotFound.class)
	public ResponseEntity<?> employeeDataNotFound(EmployeeDataNotFound ednf){
		return new ResponseEntity<>(HttpStatus.OK);
		
	}
	@ExceptionHandler(EmployeeLeaveDataNotFoundException.class)
	public ResponseEntity<?> CreditLeaveDataNitFound(EmployeeLeaveDataNotFoundException cednf){
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
	

}
