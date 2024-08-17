package com.acintyo.customexceptions;

public class EmployeeAlreadyPresent extends RuntimeException {
	
	//private static final long serialVersionUID = 1L;
	//private boolean status;
	//private String msg;
	public EmployeeAlreadyPresent(String msg) {
		super(msg);
	}
	

}
