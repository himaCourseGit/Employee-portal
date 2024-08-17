package com.acintyo.customexceptions;

public class LeaveRequestNotFound extends RuntimeException {
	   private String msg;
	   public LeaveRequestNotFound(String msg) {
		   this.msg=msg;
	   }
	   

}
