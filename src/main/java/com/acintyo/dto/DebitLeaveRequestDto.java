package com.acintyo.dto;

import java.time.LocalDate;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class DebitLeaveRequestDto {

	private String employeeId;
	private LocalDate month;
	private Double debitLeaves;
	private String leaveType;
	

}
