package com.acintyo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T>{

	private  boolean status;
	private String message;
	private  T data;
	
	public ApiResponse(boolean status,String message) {
		this.message=message;
		this.status=status;
	}
}
