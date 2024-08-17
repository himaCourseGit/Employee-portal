package com.acintyo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import static org.springframework.beans.BeanUtils.copyProperties;

import com.acintyo.Entity.EmployeeDirectory;
import com.acintyo.customexceptions.EmployeeDataNotFound;
import com.acintyo.dto.ApiResponse;
import com.acintyo.dto.EmployeeDirectoryRequestDto;
import com.acintyo.dto.EmployeeDirectoryResponseDto;
import com.acintyo.repository.IEmployeeDirectoryRepository;
import com.acintyo.repository.IEmployeeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeDirctoryServiceImpl implements IEmployeeDirectoryService {
	
	@Autowired
	private IEmployeeDirectoryRepository employeeDirectoryRepository;
	
	@Autowired
	private IEmployeeRepository employeerepository;

	@Override
	public ApiResponse<?> RegisterEmployeeDirectory(EmployeeDirectoryRequestDto dto) {
		Optional<EmployeeDirectory> optional=employeeDirectoryRepository.findByEmployeeId(dto.getEmployeeId());
		if(optional.isPresent()) {
			EmployeeDirectory employeedirectory=new EmployeeDirectory();
			employeedirectory.setEmployeeId(dto.getEmployeeId());
			employeedirectory.setId(dto.getId());
			employeedirectory.setEmail(dto.getEmail());
			employeedirectory.setMobileNumber(dto.getMobileNumber());
			employeedirectory.setName(dto.getName());
			employeedirectory.setRole(dto.getRole());
			employeedirectory=employeeDirectoryRepository.save(employeedirectory);
			EmployeeDirectoryResponseDto response=new EmployeeDirectoryResponseDto();
			response.setEmployeeId(employeedirectory.getEmployeeId());
			response.setEmail(employeedirectory.getEmail());
			response.setId(employeedirectory.getId());
			response.setMobileNumber(employeedirectory.getMobileNumber());
			response.setName(employeedirectory.getName());
			response.setRole(employeedirectory.getRole());
			return new ApiResponse<>(true,"your requested employee directory"+dto.getEmployeeId(),response);
		}
		return new ApiResponse<>(true,"EmployeeId is not exists "+dto.getEmployeeId());
	}

	@Override
	public Page<?> getAllEmployeeDirectoryData(Pageable pageable) {
		List<EmployeeDirectoryResponseDto> empDirectoryDtoList = new ArrayList<>();
		Page<EmployeeDirectory> findall=employeeDirectoryRepository.findAll(pageable);
		if(findall.isEmpty()) {
			throw new EmployeeDataNotFound("Employee data not found");
		}
		findall.forEach(e->{
			EmployeeDirectoryResponseDto employeeDirectoryResponseDto=new EmployeeDirectoryResponseDto();
			copyProperties(e,employeeDirectoryResponseDto);
			empDirectoryDtoList.add(employeeDirectoryResponseDto);
			});
		
		
		return new PageImpl<>(empDirectoryDtoList, findall.getPageable(),findall.getTotalElements());		
	}

	@Override
	public ApiResponse<?> getEmployeeDirectoryByEmployeeId(String employeeId) {
		Optional<EmployeeDirectory> findById=employeeDirectoryRepository.findByEmployeeId(employeeId);
		if(findById.isEmpty()) {
			throw new EmployeeDataNotFound("Employee is not present with the given Id"+employeeId);
			
		}
		EmployeeDirectory employeeDirectory = findById.get();	
		EmployeeDirectoryResponseDto response=new EmployeeDirectoryResponseDto();
		copyProperties(employeeDirectory, response);
	    return new ApiResponse<>(true,"Employee Data",response);
	}

	@Override
	public ApiResponse<?> updateEmployeeDirectoryById(String employeeId, EmployeeDirectoryRequestDto requestDto) {
		Optional<EmployeeDirectory> optionalEmployeeDirectory=employeeDirectoryRepository.findByEmployeeId(employeeId);
		if(optionalEmployeeDirectory.isEmpty()) {
			throw new EmployeeDataNotFound("Employeee not present with given id"+employeeId);
		}
		EmployeeDirectory employeeDitectory=optionalEmployeeDirectory.get();
		//employeeDitectory.setEmployeeId(requestDto.getEmployeeId());
		employeeDitectory.setEmail(requestDto.getEmail());
		employeeDitectory.setMobileNumber(requestDto.getMobileNumber());
		employeeDitectory.setName(requestDto.getName());
		employeeDitectory.setRole(requestDto.getRole());
		return new ApiResponse<>(true,"Successfully updated Employee Directory: "+employeeId,employeeDitectory);
	}

	@Override
	public ApiResponse<?> deleteAllEmployeeData() {
		employeeDirectoryRepository.deleteAll();
		return new ApiResponse<>(true,"Employee directory data deleted Successfully",List.of());
	}

	@Override
	public ApiResponse<?> deleteByEmployeeId(String EmployeeId) {
		Optional<EmployeeDirectory> OptionalDeleteById=employeeDirectoryRepository.findByEmployeeId(EmployeeId);
		employeeDirectoryRepository.delete(OptionalDeleteById.get());
		return new ApiResponse<>(true,"Employee Directory data deleted successfully"+EmployeeId,List.of());
	}
	
}


