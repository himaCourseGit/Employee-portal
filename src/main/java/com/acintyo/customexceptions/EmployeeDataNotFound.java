package com.acintyo.customexceptions;

public class EmployeeDataNotFound extends RuntimeException {
	
	public EmployeeDataNotFound(String msg) {
		super(msg);
		
	}

}
