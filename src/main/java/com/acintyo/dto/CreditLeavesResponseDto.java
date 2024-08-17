package com.acintyo.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditLeavesResponseDto {
	
	private String employeeId;
	private LocalDate month;
	private Double creditLeaves;
	private String leaveType;

	

}
