package com.acintyo.dto;

import lombok.Data;

@Data
public class ManagingEmployeeDirectoryRequestDto {

	private String employeeId;
	private String teamLeadId;
	private String managerId;
	private String ceoId;
}
