package com.acintyo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.acintyo.Entity.Admin;
import com.acintyo.customexceptions.AdminNotFoundException;
import com.acintyo.dto.AdminRequestDto;
import com.acintyo.dto.AdminResponseDto;
import com.acintyo.mappers.AdminMapper;
import com.acintyo.repository.AdminRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService{
	
	
	private final AdminRepository adminRepository;
	
	private final AdminMapper  adminMapper;

	@Override
	public AdminResponseDto adminLogin(AdminRequestDto requestDto) {
		System.out.println(requestDto.getUserName());
		System.out.println(requestDto.getPassword());
	 Optional<Admin> optional=adminRepository.findByUserNameAndPassword(requestDto.getUserName(),requestDto.getPassword());
	   
	  if(optional.isPresent()) {
		   Admin admin=optional.get();
		   AdminResponseDto adminResponseDto=new AdminResponseDto();
		   BeanUtils.copyProperties(admin, adminResponseDto);
		   return adminResponseDto;
	   }
	   throw new AdminNotFoundException("Invalid Creadentials");
	}
	
	
	
	

	@Override
	public List<AdminResponseDto> getAllAdmins() {
		List<Admin> admins=adminRepository.findAll();
		List<AdminResponseDto>dtos=adminMapper.toResponseDtoList(admins);
		return dtos;
	}

}
