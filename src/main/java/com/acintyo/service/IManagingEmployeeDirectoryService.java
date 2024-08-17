package com.acintyo.service;

import com.acintyo.Entity.Employee;
import com.acintyo.Entity.EmployeeDirectory;
import com.acintyo.dto.ApiResponse;
import com.acintyo.dto.ManagingEmployeeDirectoryRequestDto;

public interface IManagingEmployeeDirectoryService {
	public ApiResponse<?> postingOfEmployeeManagingDirectory(String employeeId,ManagingEmployeeDirectoryRequestDto managingrequestdto);
	public ApiResponse<?> fetchingOfEmployeeManagingDirectory(String employeeId);
	
	

}
