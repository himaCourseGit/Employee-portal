package com.acintyo.Entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="CreditLeaves")
public class CreditLeaves {
	
	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	@Column(columnDefinition = "INT default 0")
	private Integer id;
	//@Column(name="EMPLOYEEID",nullable = false)
	private String employeeId;
	//@Column(name="MONTH")
	private LocalDate month;
	//@Column(name="CREDITLEAVES")
	private Double creditLeaves;
	//@Column(name="LEAVETYPE")
	private String leaveType;

}
