package com.acintyo.customexceptions;

public class AdminNotFoundException extends RuntimeException{

	public AdminNotFoundException(String msg) {
		super(msg);
	}
}
