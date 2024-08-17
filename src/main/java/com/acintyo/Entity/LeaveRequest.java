package com.acintyo.Entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="LeaveRequest")

public class LeaveRequest {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	//@Column(name="ID")
	private Integer id;
	//@Column(name="EMPLOYEEID")
	private String employeeId;
	
	//private String month;
	//@Column(name="FROMDATE")
	private LocalDate fromDate;
	//@Column(name="TODATE")
	private LocalDate toDate;
	//@Column(name="REQUESTDAYS")
	private Double requestDays;
	//@Column(name="ISHALFDAYLEAVE")
	private String isHalfDayLeave;
	//@Column(name="STATUSBYADMIN")
	private String statusByAdmin;
	//@Column(name="STATUSBYUSER")
	private String statusByUser;
	//@Column(name="APPROVEDBY")
	private String approvedBy;
	//@Column(name="ISAPROVED")
	private String isAproved;
	
	//@Column(name="LEAVETYPE")
	private String leaveType;
	//@Column(name="DESCRIPTION")
	private String description;
	
	@CreationTimestamp
	//@Column(name="CREATEDDATE")
	private LocalDateTime createdDate;
	//@Column(name="CREATEDBY")
	private String createdBy;
	
	@UpdateTimestamp
	//@Column(name="UPDATEDDATE")
	private LocalDateTime updatedDate;
	//@Column(name="UPDATEDBY")
	private String updatedBy;
	//@Column(name="ISACTIVE")
	private String isActive;
	
}

