package com.acintyo.customexceptions;

public class EmployeeIdAndPasswordNotFoundException extends RuntimeException {
	
	public EmployeeIdAndPasswordNotFoundException(String msg) {
		super(msg);
	}

}
