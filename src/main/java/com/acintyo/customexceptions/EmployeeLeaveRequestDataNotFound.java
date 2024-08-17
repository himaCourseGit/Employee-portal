package com.acintyo.customexceptions;

public class EmployeeLeaveRequestDataNotFound extends RuntimeException{
	public EmployeeLeaveRequestDataNotFound(String msg) {
		super(msg);
	}

}
