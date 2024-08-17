package com.acintyo.Entity;

import java.time.LocalDate;

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
@Table(name="DebitLeaves")
public class DebitLeaves {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	//@Column(name="EMPLOYEEID",nullable = false)
	private String EmployeeId;
	//@Column(name="MONTH")
	private LocalDate month;
	//@Column(name="DEBITLEAVES")
	private Double debitLeaves;
	//@Column(name="LEAVETYPE")
	private String leaveType;

}
