package com.acintyo.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.acintyo.dto.AdminRequestDto;
import com.acintyo.dto.AdminResponseDto;
import com.acintyo.dto.ApiResponse;
import com.acintyo.dto.EmployeeDirectoryRequestDto;
import com.acintyo.dto.EmployeeDirectoryResponseDto;
import com.acintyo.dto.EmployeeRequestDto;
import com.acintyo.dto.ManagingEmployeeDirectoryRequestDto;
import com.acintyo.dto.ResponseDto;
import com.acintyo.service.AdminService;
import com.acintyo.service.IEmployeeDirectoryService;
import com.acintyo.service.IEmployeeService;
import com.acintyo.service.ILeaveRequestService;
import com.acintyo.service.IManagingEmployeeDirectoryService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AdminService service;
	
	@Autowired
	private ILeaveRequestService leaveRequestService;
	
	@Autowired
	private IEmployeeService employeeService;
	
	@Autowired
	private IEmployeeDirectoryService employeeDirectoryService;
	
	@Autowired
	private IManagingEmployeeDirectoryService iManagingEmployeeDirectoryService;
	
	@PostMapping("/login")
	public ResponseEntity<?> adminLogin(@RequestBody AdminRequestDto dto){
		AdminResponseDto responseDto=service.adminLogin(dto);
		ApiResponse<AdminResponseDto>apiResponse=new ApiResponse<AdminResponseDto>(true, "login successfull", responseDto);
		return ResponseEntity.ok(apiResponse);
	}
	
	@GetMapping("/getAll-admins")
	public ResponseEntity<?> getAllAdmins(){
		List<AdminResponseDto>adminResponseDtos=service.getAllAdmins();
		ApiResponse<List<AdminResponseDto>>apiResponse=new ApiResponse<List<AdminResponseDto>>(true, "login successfull", adminResponseDtos);
		return ResponseEntity.ok(apiResponse);

	}
	
	@GetMapping("/GetAllEemployee-byStatus")
	public ResponseEntity<?> getAllEmployeeData(@RequestParam String status,@PageableDefault Pageable pageable) {
		ApiResponse<?> employeedata = employeeService.getAllEmployeesData(status, pageable);
		ApiResponse<?> apiResponse = new ApiResponse<>(true, "Employee data fetched successfully", employeedata);
         return ResponseEntity.ok(employeedata);
	}
	
	@PutMapping("/emp-aproval-ByAadharNO")
	public ResponseEntity<?> employeeAproval(@RequestParam Long aadharNo,
			@RequestParam String IsAproved){
 	ResponseDto dto=employeeService.employeeApproval(aadharNo, IsAproved);
		ApiResponse<ResponseDto> apiResponse=new ApiResponse<ResponseDto>(true, "emp aproved successfuly", dto);
		return ResponseEntity.ok(apiResponse);
	}
	
	@GetMapping("/get/leaveRequest")
	public ResponseEntity<?> findByApproval(@RequestParam String status, @PageableDefault Pageable pageable){
		ApiResponse<?> response =leaveRequestService.findBYApproved(status, pageable);
		//ApiResponse<?> apiResponse = new ApiResponse<>(true, "Leave Requests", response);
		return ResponseEntity.ok(response);
	}
	
	@PutMapping("/updateLeaveRequest-ById")
	public ResponseEntity<?> updateLeaveStatusByAdmin(@RequestParam Integer id, @RequestParam String approvedStatus, @RequestParam String employeeId,@RequestParam String approvedBy){
		ApiResponse<?> response=leaveRequestService.updateByApproved(id, approvedStatus,employeeId,approvedBy);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@GetMapping("/GetEmployeeByEmpId/{empId}")
	public ResponseEntity<?> getEmployeeDataByEmployeeId(@PathVariable("empId") String empId){
		ResponseDto getById=employeeService.getEmployeeDataByEmployeeId(empId);
		return new ResponseEntity<>(getById,HttpStatus.OK);	
	}
	
	@PutMapping("/UpdateEmployeeByEmpId/{empId}")
	public ResponseEntity<?> updateEmployeeByEmployeeId(@PathVariable("empId") String empId,@RequestBody EmployeeRequestDto dto){
		ApiResponse<?> updatedata=employeeService.updateEmployeeDataByEmployeeId(empId, dto);
		return new ResponseEntity<>(updatedata,HttpStatus.OK) ;	
	}
	@GetMapping("/totalNoOfEmployees")     
	public ResponseEntity<?> getTotalNoOfEmployees(){
		ApiResponse<?> response=employeeService.getTotalNoOfEmployees();
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@PostMapping("/postingOfEmployeeDirectory")
	public ResponseEntity<?> employeeDirectoryRegistartion(@RequestBody EmployeeDirectoryRequestDto employeeDirectoryrequestDto){
		ApiResponse<?> response=employeeDirectoryService.RegisterEmployeeDirectory(employeeDirectoryrequestDto);
		return new ResponseEntity<> (response,HttpStatus.OK) ;
	}
	@PostMapping("/registrationOfManagingDirectoryIds")
	public ResponseEntity<?> registrationOfManagingDirectory(@RequestParam String employeeId,ManagingEmployeeDirectoryRequestDto managingEmployeeDirectoryRequestDto){
		ApiResponse<?> registrationOfManagingDirectoryIds=iManagingEmployeeDirectoryService.postingOfEmployeeManagingDirectory(employeeId,managingEmployeeDirectoryRequestDto);
		return new ResponseEntity<>(registrationOfManagingDirectoryIds,HttpStatus.OK);	
	}
}
