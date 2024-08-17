package com.acintyo.service;

import java.util.List;

import com.acintyo.dto.AdminRequestDto;
import com.acintyo.dto.AdminResponseDto;

public interface AdminService {
	
	AdminResponseDto adminLogin(AdminRequestDto dto);
	
	List<AdminResponseDto> getAllAdmins();

}
