package com.acintyo.dto;

import lombok.Data;

@Data
public class EmployeeDirectoryResponseDto {

	private Integer id;
	private String employeeId;
	private String role;
	private String email;
	private Long mobileNumber;
	private String name;
}
