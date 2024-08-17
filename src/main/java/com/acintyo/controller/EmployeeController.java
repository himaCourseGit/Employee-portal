package com.acintyo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.acintyo.dto.ApiResponse;
import com.acintyo.dto.EmployeeDirectoryRequestDto;
import com.acintyo.dto.EmployeeDirectoryResponseDto;
import com.acintyo.dto.EmployeeRequestDto;
import com.acintyo.dto.LoginDto;
import com.acintyo.dto.ManagingEmployeeDirectoryRequestDto;
import com.acintyo.dto.ResetPasswordDto;
import com.acintyo.dto.ResponseDto;
import com.acintyo.service.CreditLeavesServiceImpl;
import com.acintyo.service.ICreditLeaveService;
import com.acintyo.service.IEmployeeDirectoryService;
import com.acintyo.service.IEmployeeService;
import com.acintyo.service.IManagingEmployeeDirectoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/Employee")
@Validated
//@CrossOrigin(origins = "http://localhost:3000",methods = {RequestMethod.POST,RequestMethod.PUT,RequestMethod.GET,RequestMethod.DELETE})
public class EmployeeController {
	
	@Autowired
	public IEmployeeService service;
	
	@Autowired
	private ICreditLeaveService creditLeaveService;
	
	@Autowired
	private IEmployeeDirectoryService employeeDirectoryService;
	
	@Autowired
	private IManagingEmployeeDirectoryService ManagingEmployeeDirectoryService;

	@PostMapping("/RegisterEmployee")
	public ResponseEntity<?> registerEmployeeData(@Valid @RequestBody  EmployeeRequestDto dto) {
		ApiResponse<?> Emp = service.registrationOfEmployee(dto);
		return new ResponseEntity<>(Emp,HttpStatus.CREATED);
	}
	
//	@GetMapping("/GetAllEmployeeData/page")
//	public ResponseEntity<?> getAllEmployeesData(String isApproved, @PageableDefault @RequestBody Pageable page){
//		ApiResponse<?> responsedto=service.getAllEmployeesData(isApproved, page);
//		ApiResponse<?> apiresponse=new ApiResponse<>(true,"List of Employee data",responsedto);
//		return ResponseEntity.ok(apiresponse);
//	}
//	
	
	
	@DeleteMapping("/deleteByAadhar/{id}")
	public ResponseEntity<?> deleteteById( @PathVariable("id") Long id){
		return new ResponseEntity<>(HttpStatus.OK);	
	}
	@DeleteMapping("/deleteall")
	public ResponseEntity<?> deleteAll(){
		return new ResponseEntity<>(HttpStatus.OK);
		
	}
	
	@PostMapping("/emp-logins")
	public ResponseEntity<?>employeeLogin(@RequestBody LoginDto dto){
		
		ResponseDto responseDto=service.empLogin(dto);
		ApiResponse<ResponseDto> apiResponse=new ApiResponse<ResponseDto>(true, "login successfull", responseDto);
		return ResponseEntity.ok(apiResponse);
	}
	
	@PutMapping("/emp-resetPassword")
	public ResponseEntity<?> resetPassword(@RequestBody @Valid ResetPasswordDto dto){
		ResponseDto responseDto=service.empResetPassword(dto);
		ApiResponse<ResponseDto> apiResponse=new ApiResponse<ResponseDto>(true, "password reset successfull", responseDto);
		return ResponseEntity.ok(apiResponse);

	}
	
	@GetMapping("/get-empBy-EmpId")
	public ResponseEntity<?> getEmpById(@RequestParam String empId){
		ResponseDto dto=service.getEmployeeDataByEmployeeId(empId);
		ApiResponse<ResponseDto> apiResponse=new ApiResponse<ResponseDto>(true, "employee data fetched successfull", dto);
		return ResponseEntity.ok(apiResponse);
	}
	
	@PutMapping("/update-empBy-EmpId")
	public ResponseEntity<?> updateEmpById(@RequestParam String empId,@RequestBody EmployeeRequestDto dto){
		ResponseDto responseDto=service.updateEmpById(empId, dto);
		ApiResponse<ResponseDto> apiResponse=new ApiResponse<ResponseDto>(true, "employee data updated successfull", responseDto);
		return ResponseEntity.ok(apiResponse);
	}
	
	@GetMapping("/totalNoOfLeaves")
	public ResponseEntity<?> totalNoOfLeaves(@RequestParam String empId){
		return creditLeaveService.totalNoOfLeaves(empId);
		
	}
	
	@GetMapping("/getAllFromEmployeeDirectory")
	public ResponseEntity<?> getAllEmployeeDirectory(@RequestBody @PageableDefault Pageable page){
		Page<?> allEmployeeDirectoryData = employeeDirectoryService.getAllEmployeeDirectoryData(page);
		return new ResponseEntity<>(allEmployeeDirectoryData,HttpStatus.OK);	
	}
	@GetMapping("/getEmployeeDirectoryDataById")
	public ResponseEntity<?> getEmployeeDirectoryData(@RequestParam String EmployeeId ){
		ApiResponse<?> getEmployeeDirectoryById=employeeDirectoryService.getEmployeeDirectoryByEmployeeId(EmployeeId);
		return  new ResponseEntity<>(getEmployeeDirectoryById,HttpStatus.OK);
		
	}
	@PutMapping("/updateEmployeeDIrectoryEmployeeId")
	public ResponseEntity<?> updateEmployeeDirectoryDataById(@RequestParam String employeeId,@RequestBody EmployeeDirectoryRequestDto requestdto ){
	ApiResponse<?> updateEmployeeDirectoryById=employeeDirectoryService.updateEmployeeDirectoryById(employeeId, requestdto);
	return new ResponseEntity<>(updateEmployeeDirectoryById,HttpStatus.OK);
	}
	@DeleteMapping("/deleteEmployeeDirectoryById")
	public ResponseEntity<?> deleteEmployeeDirectoryDataById(@RequestParam String employeeId){
		ApiResponse<?> deleteEmployeeDirectoryById=employeeDirectoryService.deleteByEmployeeId(employeeId);
		return new ResponseEntity<>(deleteEmployeeDirectoryById,HttpStatus.OK);
		
	}
	@DeleteMapping("/deleteAllEmployeeDirectory")
	public ResponseEntity<?> deleteAllEmployeeDirectoryData(){
		ApiResponse<?> deleteAllEmployeeDirectory=employeeDirectoryService.deleteAllEmployeeData();
		return  new ResponseEntity<>(deleteAllEmployeeDirectory,HttpStatus.OK);	
	}
	@GetMapping("/getManagingDirectoryDataBYEmployeeId")
	public ResponseEntity<?> FetchingOFManagingEmployeeDirectoryData(String employeeId){
		ApiResponse<?> gettingManagingDirectoryDetailsById=ManagingEmployeeDirectoryService.fetchingOfEmployeeManagingDirectory(employeeId);
		return new ResponseEntity<>(gettingManagingDirectoryDetailsById,HttpStatus.OK);	
	}
	
	
}
