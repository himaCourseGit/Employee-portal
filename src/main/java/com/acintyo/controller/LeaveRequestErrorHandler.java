package com.acintyo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.acintyo.customexceptions.LeaveRequestNotFound;

public class LeaveRequestErrorHandler {
	
	@ExceptionHandler(LeaveRequestNotFound.class)
	public ResponseEntity<?> leaveRequestNotFound(LeaveRequestNotFound lrnf){
		return new ResponseEntity<LeaveRequestNotFound>(lrnf,HttpStatus.OK);	
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>> handleAllProblems(MethodArgumentNotValidException e) {
		
		Map<String, String> map = new HashMap<>();
		
		e.getBindingResult().getFieldErrors().forEach(x->map.put(x.getField(), x.getDefaultMessage()));
		return ResponseEntity.badRequest().body(map);
	}
	

}
