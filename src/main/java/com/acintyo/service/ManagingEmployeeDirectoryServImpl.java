package com.acintyo.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acintyo.Entity.Employee;
import com.acintyo.Entity.EmployeeDirectory;
import com.acintyo.Entity.ManagingEmployeeDirectory;
import com.acintyo.customexceptions.EmployeeManagingDirectoryNotFound;
import com.acintyo.dto.ApiResponse;
import com.acintyo.dto.ManagingEmployeeDirectoryRequestDto;
import com.acintyo.dto.ManagingEmployeeDirectoryResponseDto;
import com.acintyo.repository.IEmployeeDirectoryRepository;
import com.acintyo.repository.IEmployeeRepository;
import com.acintyo.repository.IManagingEmoployeeDirectoryRepository;

@Service
public class ManagingEmployeeDirectoryServImpl implements IManagingEmployeeDirectoryService {

	@Autowired
	private IManagingEmoployeeDirectoryRepository iManagingEmoployeeDirectoryRepository;

	@Autowired
	private IEmployeeDirectoryRepository iEmployeeDirectoryRepository;
	@Autowired
	private IEmployeeRepository employeeRepository;

	@Override
	public ApiResponse<?> postingOfEmployeeManagingDirectory(String employeeId,
			ManagingEmployeeDirectoryRequestDto managingrequestdto) {
		Optional<ManagingEmployeeDirectory> managingEmployeeDirectory = iManagingEmoployeeDirectoryRepository
				.findByEmployeeId(employeeId);
		if (managingEmployeeDirectory.isEmpty()) {

			throw new EmployeeManagingDirectoryNotFound(
					"EmployeeManagingDirectory is not exist with employeeId: " + employeeId);
		}

		ManagingEmployeeDirectory managingdirectory = new ManagingEmployeeDirectory();
		managingdirectory.setCeoId(managingrequestdto.getCeoId());
		managingdirectory.setEmployeeId(managingrequestdto.getEmployeeId());
		managingdirectory.setManagerId(managingrequestdto.getManagerId());
		managingdirectory.setTeamLeadId(managingrequestdto.getTeamLeadId());
		ManagingEmployeeDirectory save = iManagingEmoployeeDirectoryRepository.save(managingdirectory);
		return new ApiResponse<>(true, "successfully registered employeeManaging directory", save);

	}

	@Override
	public ApiResponse<?> fetchingOfEmployeeManagingDirectory(String employeeId) {
		Optional<ManagingEmployeeDirectory> getDirectory = iManagingEmoployeeDirectoryRepository
				.findByEmployeeId(employeeId);
		ManagingEmployeeDirectoryResponseDto dto = new ManagingEmployeeDirectoryResponseDto();
		if (getDirectory.isPresent()) {
			ManagingEmployeeDirectory managingEmployeeDirectory = getDirectory.get();
			Optional<EmployeeDirectory> findByEmployeeId = iEmployeeDirectoryRepository
					.findByEmployeeId(managingEmployeeDirectory.getCeoId());
			Optional<EmployeeDirectory> findByEmployeeId2 = iEmployeeDirectoryRepository
					.findByEmployeeId(managingEmployeeDirectory.getManagerId());
			Optional<EmployeeDirectory> findByEmployeeId4 = iEmployeeDirectoryRepository
					.findByEmployeeId(managingEmployeeDirectory.getTeamLeadId());
			Optional<Employee> findByEmployeeId3 = employeeRepository.findByEmployeeId(employeeId);
			if (findByEmployeeId.isPresent() && findByEmployeeId2.isPresent() && findByEmployeeId4.isPresent()
					&& findByEmployeeId3.isPresent()) {
				EmployeeDirectory ceoDirectory = findByEmployeeId.get();
				EmployeeDirectory managerDirectory = findByEmployeeId2.get();
				Employee employee = findByEmployeeId3.get();
				EmployeeDirectory employeeDirectory = findByEmployeeId4.get();
				dto.setCeoEmail(ceoDirectory.getEmail());
				dto.setCeoMobileNumber(ceoDirectory.getMobileNumber().toString());
				dto.setCeoName(ceoDirectory.getName());
				dto.setEmployeeMobileNumber(employeeDirectory.getMobileNumber().toString());
				dto.setEmpEmail(employeeDirectory.getEmail());
				dto.setEmpName(employeeDirectory.getName());
				dto.setManagerEmail(managerDirectory.getEmail());
				dto.setManagerName(managerDirectory.getName());
				dto.setManagerMobileNumber(managerDirectory.getMobileNumber().toString());
				dto.setTeamLeadEmail(employeeDirectory.getEmail());
				dto.setTeamLeadMobileNumber(employeeDirectory.getMobileNumber().toString());
				dto.setTeamLeadName(employeeDirectory.getName());
			}
			;
			throw new EmployeeManagingDirectoryNotFound("Employee mananging directory not found:" + employeeId);
		}
		return new ApiResponse<>(true,"EmployeeDirectory returned successfully: "+employeeId,dto) ;
	}
}
