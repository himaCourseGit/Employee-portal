package com.acintyo.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import com.acintyo.Entity.LeaveRequest;
import com.acintyo.dto.LeaveResponseDto;

@Mapper(componentModel = "spring")

public interface LeaveRequestMapper {
	public List<LeaveResponseDto> toLeaveResponseDtoList(List<LeaveRequest> leaverequest);
	public LeaveResponseDto toLeaveResponseDto(LeaveRequest leaverequest);
	
	

}
