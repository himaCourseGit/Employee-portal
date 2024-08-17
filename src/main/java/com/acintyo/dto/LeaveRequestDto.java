package com.acintyo.dto;

import java.time.LocalDate;

import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Component
@PropertySource("classpath:validations.properties")
public class LeaveRequestDto {
	

	@NotBlank(message = "{leaverequest.validation.notblank.employeeId}")
	private String employeeId;

	@NotNull(message = "{leaverequest.validation.notnull.fromDate}")
	private LocalDate fromDate;

	@NotNull(message = "{leaverequest.validation.notnull.toDate}")
	private LocalDate toDate;

	@NotNull(message = "{leaverequest.validation.notnull.numberOfDays}")
	private Double requestDays;

	@NotBlank(message="{leaverequest.validation.notblank.description}")
	private String description;
	
    @NotBlank(message="{leaverequest.validation.notblank.leavetype}") 
	private String leaveType;
  
    @NotBlank(message="{leaverequest.validation.notblank.isHalfDayLeave}")
	private String isHalfDayLeave;
	
	@NotBlank(message="{leaverequest.validation.notblank.statusByAdmin}")
	private String statusByAdmin;
	
	@NotBlank(message="{leaverequest.validation.notblank.statusByUser}")
	private String statusByUser;
	
	@NotBlank(message="{leaverequest.validation.notblank.approvedBy}")
	private String approvedBy;
	
	@NotBlank(message="{leaverequest.validation.notblank.isAproved")
	private String isAproved;
	
	

	

}
