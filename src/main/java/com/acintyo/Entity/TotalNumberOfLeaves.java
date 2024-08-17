package com.acintyo.Entity;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TotalNumberOfLeaves {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	//private String month;
	
	private String employeeId;
	
    private Double compOffLeaves;
	
	private Double earnedLeaves;
	
	private Double totalNoOfLeaves;
	
	//private LocalDate fromDate;
	
	//private LocalDate toDate;
	
	

}
