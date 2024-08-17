package com.acintyo.service;

import com.acintyo.dto.ApiResponse;
import com.acintyo.dto.DebitLeaveRequestDto;

public interface DebitLeaveService  {
	public ApiResponse<?> getByEmpId(String employeeId,DebitLeaveRequestDto dto);
	public ApiResponse<?> updateByDebitLeaves(Integer id,String employeeId,Double requestDays);
	

}
