package com.acintyo.Entity;



import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admin {
	
	@Id
	//@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	
	private String userName;
	
	private String password;

}