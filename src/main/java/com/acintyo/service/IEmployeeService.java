package com.acintyo.service;

import org.springframework.data.domain.Pageable;

import com.acintyo.dto.ApiResponse;
import com.acintyo.dto.EmployeeRequestDto;
import com.acintyo.dto.LoginDto;
import com.acintyo.dto.ResetPasswordDto;
import com.acintyo.dto.ResponseDto;

public interface IEmployeeService {
	public ApiResponse<?> registrationOfEmployee(EmployeeRequestDto dto);
	
	public ApiResponse<?> getAllEmployeesData(String isApproved, Pageable pageable);
	
	public ResponseDto employeeApproval(Long aadharnumber,String Isproved);
	
	public ResponseDto empResetPassword(ResetPasswordDto passwordDto);
	
	public ResponseDto empLogin(LoginDto dto);
	
	public ResponseDto getEmployeeDataByEmployeeId(String empId);
	
	public ResponseDto updateEmpById(String empId,EmployeeRequestDto dto);
	
	//public ApiResponse<?> getEmployeeDataByEmployeeId(String employeeId);
	public ApiResponse<?> updateEmployeeDataByEmployeeId(String EmpId,EmployeeRequestDto dto);
	
	public ResponseDto deleteById(String empId);
	
	public ApiResponse<?> deleteAll();

//	public ApiResponse<?> getTotalNoOfLeaves(String empId);
	
	ApiResponse<?> getTotalNoOfEmployees();
	
	
}
