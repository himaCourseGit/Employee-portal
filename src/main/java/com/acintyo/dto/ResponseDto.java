package com.acintyo.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto {
	
    private Long aadharnumber;
	private String name;
	private String employeeId;
	private String dateOfBirth;
	private String dateOfJoining;
	private Long mobileNumber;
	private String email;
	private String imgUrl;
	private String password;
	
}
