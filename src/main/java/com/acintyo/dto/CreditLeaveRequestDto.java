package com.acintyo.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditLeaveRequestDto {
	private String employeeId;
	private LocalDate month;
	private Integer creditLeaves;
	private String leaveType;
}
