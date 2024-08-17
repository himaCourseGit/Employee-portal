package com.acintyo.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DebitLeavesResoponseDto {
	private String EmployeeId;
	private LocalDate month;
	private Double debitLeaves;
	private String leaveType;

}
