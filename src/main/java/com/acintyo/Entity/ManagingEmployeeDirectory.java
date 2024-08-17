package com.acintyo.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ManagingEmployeeDirectory {
	
	@Id
	private String employeeId;
	private String teamLeadId;
	private String managerId;
	private String ceoId;
	
	

}
