package com.acintyo.dto;

import java.time.LocalDate;

import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.acintyo.validator.ValidAadhaarNumber;
import com.acintyo.validator.ValidMobileNumber;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Component
@PropertySource("classpath:validations.properties")

public class EmployeeRequestDto {
	
//	@NotNull(message="{validation.notblank.aadharnumber}")
//	//@Pattern(regexp = "\\d{12}", message = "Aadhaar number must be a 12-digit number")
//	@ValidAadhaarNumber
//	private Long aadharnumber;
//	
//	@NotBlank(message="{validation.notblank.name}")
//	private String name;
//	
//	@NotNull(message="{validation.notblank.dateOfBirth}")
//	private LocalDate dateOfBirth;
//	
//	@NotNull(message="{validation.notnull.dateOfJoining}")
//	private LocalDate dateOfJoining;
//	
//	@NotNull(message="{validation.notnull.mobileNumber}")
//	//@Pattern(regexp="^{10}$",message="{validation.pattern.mobileNumber}")
//	private Long mobileNumber;
//	
//	@NotBlank(message="{validation.notblank.emailId}")
//	//@Pattern(regexp="^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$",message="{validation.pattern.emailId}")
//	@Email(message = "eEmail format must be required")
//	private String emailId;
	@NotNull(message="{validation.notblank.aadharnumber}")
	@ValidAadhaarNumber
	private Long aadharnumber;
	@NotBlank(message="{validation.notblank.name}")
	private String name;
	@NotNull(message="{validation.notblank.dateOfBirth}")
	private LocalDate dateOfBirth;
	@NotNull(message="{validation.notnull.dateOfJoining}")
	private LocalDate dateOfJoining;
	@NotNull(message="{validation.notnull.mobileNumber}")
	@ValidMobileNumber
	private Long mobileNumber;
	@NotBlank(message="{validation.notblank.email}")
	@Email(message = "eEmail format must be required")
	private String email;
	//@NotBlank(message="{validation.notblank.imgUrl}")
	private String imgUrl;
	
}
