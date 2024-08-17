package com.acintyo.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import com.acintyo.Entity.Admin;
import com.acintyo.Entity.Employee;
import com.acintyo.dto.AdminResponseDto;
import com.acintyo.dto.ResponseDto;

@Mapper(componentModel = "spring")
public interface AdminMapper {
	public List<AdminResponseDto> toResponseDtoList(List<Admin> emps);
	public AdminResponseDto toResponseDto(Admin emp);
}
