package com.acintyo.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import com.acintyo.Entity.Employee;
import com.acintyo.dto.ResponseDto;

@Mapper(componentModel = "spring")

public interface EmployeeMapper {
	public List<ResponseDto> toResponseDtoList(List<Employee> emps);
	public ResponseDto toResponseDto(Employee emp);

}
