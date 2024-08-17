package com.acintyo.Entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Employee")
public class Employee {
	@Id
	@Column(columnDefinition = "BIGINT default 0")
	//@Column(name="AADHARNUMBER",columnDefinition = "BIGINT default 0")
	private Long aadharnumber;
	
	//@Column(name="NAME")
	private String name;
	//@Column(name="EMPLOYEEID")
	private String employeeId;
	//@Column(name="DATEOFBIRTH")
	private LocalDate dateOfBirth;
	//@Column(name="DATEOFJOINING")
	private LocalDate dateOfJoining;
	//@Column(name="MOBILENUMBER")
	private Long mobileNumber;
	//@Column(name="EMAIL")
	private String email;
	//@Column(name="IMGURL")
	private String imgUrl;
	
	//@Column(name="EMPLOYEESTATUS")
	private String employeeStatus;
	//@Column(name="PASSWORD")
	private String password;
	//@Column(name="ISAPPROVED")
	private String isApproved;
   	
	
    //@Column(name="CREATEDBY")
	private String createdBy;
    //@Column(name="CREATEDAT")
	private LocalDateTime createdAt;
    //@Column(name="UPDATEDBY")
	private String updatedBy;
    //@Column(name="UPDATEDDATE")
	private LocalDateTime updatedDate;
    //@Column(name="ISACTIVE")
    private String isActive;
    
    
}
