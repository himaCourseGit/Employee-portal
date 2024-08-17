package com.acintyo.dto;

import lombok.Data;

@Data
public class EmployeeDirectoryRequestDto {
	
	private Integer id;
	private String employeeId;
	private String role;
	private String email;
	private Long mobileNumber;
	private String name;
	

}
