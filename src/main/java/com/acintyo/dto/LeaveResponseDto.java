package com.acintyo.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class LeaveResponseDto {
	
	private Integer id;
	private String employeeId;
	//private String month;
	private String fromDate;
	private String toDate;
	private String requestDays;
	private String isHalfDayLeave;
	private String statusByAdmin;
	private String statusByUser;
	private String leaveType;
	private String description;

}
