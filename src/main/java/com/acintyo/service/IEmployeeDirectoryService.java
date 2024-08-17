package com.acintyo.service;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.acintyo.dto.ApiResponse;
import com.acintyo.dto.EmployeeDirectoryRequestDto;

public interface IEmployeeDirectoryService {
	public ApiResponse<?>  RegisterEmployeeDirectory(EmployeeDirectoryRequestDto employeeDirectoryRequestDto);
	public Page<?> getAllEmployeeDirectoryData(Pageable pageable);
	public ApiResponse<?> getEmployeeDirectoryByEmployeeId(String employeeId);
	public ApiResponse<?> updateEmployeeDirectoryById(String employeeId,EmployeeDirectoryRequestDto requestDto);
	public ApiResponse<?> deleteAllEmployeeData();
	public ApiResponse<?> deleteByEmployeeId(String EmployeeId);

}
