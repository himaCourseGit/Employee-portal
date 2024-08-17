package com.acintyo.customexceptions;

public class EmployeeLeaveDataNotFoundException extends RuntimeException {
	public EmployeeLeaveDataNotFoundException(String msg) {
		super(msg);
	}

}
