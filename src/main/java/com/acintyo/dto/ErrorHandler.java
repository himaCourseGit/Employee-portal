package com.acintyo.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@SuppressWarnings("serial")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ErrorHandler implements Serializable{
	private LocalDateTime time;
	private String Status;
	private String msg;
	
	

}
