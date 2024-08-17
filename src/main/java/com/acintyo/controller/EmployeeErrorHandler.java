package com.acintyo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.acintyo.customexceptions.AdminNotFoundException;
import com.acintyo.customexceptions.EmployeeAlreadyPresent;
import com.acintyo.customexceptions.EmployeeDataNotFound;
import com.acintyo.customexceptions.EmployeeIdAndPasswordNotFoundException;
import com.acintyo.dto.ApiResponse;
import com.acintyo.dto.ResponseDto;

@RestControllerAdvice
public class EmployeeErrorHandler {
	
	@ExceptionHandler(EmployeeAlreadyPresent.class)
	public ResponseEntity<?> employeeAlreadyPresent(EmployeeAlreadyPresent eap) {
		return ResponseEntity.ok(new ApiResponse<>(true,eap.getMessage(), List.of()));
				
	}

	@ExceptionHandler(EmployeeDataNotFound.class)
	public ResponseEntity<ApiResponse<ResponseDto>> employeeDataNotFound(EmployeeDataNotFound ednf) {
		ApiResponse<ResponseDto> apiResponse=new ApiResponse<ResponseDto>(false, ednf.getMessage(),null);
		return ResponseEntity.ok(apiResponse);
	}
	@ExceptionHandler(EmployeeIdAndPasswordNotFoundException.class)
	public ResponseEntity<ApiResponse<ResponseDto>> EmployeeIdAndPasswordNotFoundException(EmployeeIdAndPasswordNotFoundException ednf) {
		ApiResponse<ResponseDto> apiResponse=new ApiResponse<ResponseDto>(false, ednf.getMessage(),null);
		return ResponseEntity.ok(apiResponse);
	}
	
	@ExceptionHandler(AdminNotFoundException.class)
	public ResponseEntity<ApiResponse<ResponseDto>> AdminNotFoundException(AdminNotFoundException ednf) {
		ApiResponse<ResponseDto> apiResponse=new ApiResponse<ResponseDto>(false, ednf.getMessage(),null);
		return ResponseEntity.ok(apiResponse);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>> handleAllProblems(MethodArgumentNotValidException e) {
		
		Map<String, String> map = new HashMap<>();
		
		e.getBindingResult().getFieldErrors().forEach(x->map.put(x.getField(), x.getDefaultMessage()));
		return ResponseEntity.badRequest().body(map);
	}

}
//new EmployeeAlreadyPresent("Employee Already exists with this aadhar", false), HttpStatus.OK);