package com.acintyo.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicReference;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.acintyo.Entity.Employee;
import com.acintyo.customexceptions.EmployeeAlreadyPresent;
import com.acintyo.customexceptions.EmployeeDataNotFound;
import com.acintyo.customexceptions.EmployeeIdAndPasswordNotFoundException;
import com.acintyo.dto.ApiResponse;
import com.acintyo.dto.EmployeeRequestDto;
import com.acintyo.dto.LoginDto;
import com.acintyo.dto.ResetPasswordDto;
import com.acintyo.dto.ResponseDto;
import com.acintyo.generators.EmployeeIdGenerator;
import com.acintyo.mappers.EmployeeMapper;
import com.acintyo.repository.IEmployeeRepository;
import com.acintyo.utils.EmailUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeServiceImpl implements IEmployeeService {

	private final IEmployeeRepository employeeRepository;
	
	private final EmailUtils emailUtils;
	
	private final  EmployeeIdGenerator employeeIdGenerator;

	private final EmployeeMapper employeeMapper;

	@Override
	public ApiResponse<?> registrationOfEmployee(EmployeeRequestDto dto) {
		Optional<Employee> optional=employeeRepository.findByAadharnumberAndEmail(dto.getAadharnumber(),
				dto.getEmail());
		if(optional.isEmpty()) {
			Employee emp = new Employee();
			emp.setAadharnumber(dto.getAadharnumber());
			emp.setName(dto.getName());
			emp.setDateOfBirth(dto.getDateOfBirth());
			emp.setDateOfJoining(dto.getDateOfJoining());
			emp.setMobileNumber(dto.getMobileNumber());
			emp.setEmail(dto.getEmail());
			emp.setEmployeeStatus("Success");
			emp.setIsApproved("N");
			emp.setImgUrl(dto.getImgUrl());
			emp.setCreatedAt(LocalDateTime.now());
			emp = employeeRepository.save(emp);
			// ApiResponse response = new ApiResponse<>(true, "user Registration has been
			// successfull");
			ApiResponse<?> response = new ApiResponse<>(true, "User registration has been succesful", List.of());
			return response;
		}
		
		throw new EmployeeAlreadyPresent("Employee allReady present with given details");
		}
       
	
	@Override
	public ApiResponse<?> getAllEmployeesData(String isApproved, Pageable pageable) {
		Page<Employee> page=employeeRepository.findByIsApproved(isApproved, pageable);
		if(page.isEmpty()) {
			Page<?> dto = new PageImpl<>(List.of(), page.getPageable(), page.getNumberOfElements());
			return new ApiResponse<>(true,"page is empty ",dto);
		}
		
		Page<?> dto =  new PageImpl<>(
				employeeMapper.toResponseDtoList(page.getContent()), 
				page.getPageable(),
				page.getTotalElements()
				);
		return new ApiResponse<>(true,"Page of objects",dto);
	}
	
/*
	@Override
	public Page<ApiResponse<?>> getAllApprovedEmployeesData(String isApproved,Pageable pageable) {
		List<ResponseDto> empDtoList = new ArrayList<>();
		Page<Employee> findall = employeeRepository.findByIsApproved(isApproved);
		if (findall.isEmpty()) {
			throw new EmployeeDataNotFound("Employee data not exist");
		} else {
			// List<ResponseDto> empDtoList=new ArrayList<>();
			int size = findall.size();
			for (int i = 0; i < size; i++) {
				ResponseDto dto = new ResponseDto();
				Employee e = findall.get(i);
				dto.setAadharnumber(e.getAadharnumber());
				dto.setName(e.getName());
				dto.setDateOfBirth(e.getDateOfBirth().toString());
				dto.setDateOfJoining(e.getDateOfJoining().toString());
				dto.setMobileNumber(e.getMobileNumber());
				dto.setEmailId(e.getEmailId());
				empDtoList.add(dto);
			}
		}
		return new ApiResponse<>(true, "Employee data", empDtoList);
	}
*/
	/*
	@Override
	public ApiResponse<?> getEmployeeDataByEmployeeId(String empId) {
		Optional<Employee> optinal = employeeRepository.findByEmployeeIdAndIsActive(empId,"Y");
		if (optinal.isPresent()) {
			ResponseDto dto = new ResponseDto();
			Employee employee = optinal.get();
			dto.setAadharnumber(employee.getAadharnumber());
			dto.setName(employee.getName());
			dto.setDateOfBirth(employee.getDateOfBirth().toString());
			dto.setDateOfJoining(employee.getDateOfJoining().toString());
			dto.setMobileNumber(employee.getMobileNumber());
			dto.setEmailId(employee.getEmailId());
			// dto.setPassword(employee.getPassword());
			return new ApiResponse<>(true, "Employee data With given empId", dto);
		}
		throw new EmployeeDataNotFound("Emp data not found with given Emp Id");
	}
*/
	@Override
	public ApiResponse<?> updateEmployeeDataByEmployeeId(String empId, EmployeeRequestDto dto) {
		Optional<Employee> optional = employeeRepository.findByEmployeeIdAndIsActive(empId,"Y");
		if (optional.isPresent()) {
			Employee employee = optional.get();
			employee.setAadharnumber(dto.getAadharnumber());
			employee.setName(dto.getName());
			employee.setDateOfBirth(dto.getDateOfBirth());
			employee.setDateOfJoining(dto.getDateOfJoining());
			employee.setMobileNumber(dto.getMobileNumber());
			employee.setEmail(dto.getEmail());
			employee.setImgUrl(dto.getImgUrl());
			employeeRepository.save(employee);
			return new ApiResponse<>(true, "Employee data updated successfully",List.of());
		}
		throw new EmployeeDataNotFound("Emp data not found with given empId");

	}
	@Override
	public ResponseDto deleteById(String empId) {
		Optional<Employee> optional=employeeRepository.findByEmployeeIdAndIsActive(empId,"Y");
		if(optional.isPresent()) {
			Employee employee=optional.get();
			employee.setIsActive("N");
			employeeRepository.save(employee);
			 ResponseDto dto=employeeMapper.toResponseDto(employee);
			 dto.setDateOfBirth(employee.getDateOfBirth().toString());
			 dto.setDateOfJoining(employee.getDateOfJoining().toString());
			return dto;		
		}
		throw new EmployeeDataNotFound("Emp data not found with given empId");
	}

	/*
	@Override
	public ApiResponse deleteAll() {
		employeeRepository.deleteAll();
		return new ApiResponse<>(true, "Employee records deleted succesfully");

	}
*/
	@Override
	public ResponseDto employeeApproval(Long aadharnumber, String Isproved) {
		Optional<Employee> optional=employeeRepository.findByAadharnumber(aadharnumber);
		if(optional.isPresent()) {
			Employee employee=optional.get();
			employee.setIsApproved(Isproved);
			
			if(Isproved.equalsIgnoreCase("Y")) {
				employee.setEmployeeId(employeeIdGenerator.employeeSequence());
				employee.setPassword(OneTimePassword(employee.getName()));
				employee.setIsActive("Y");
				employeeRepository.save(employee);
				
				ResponseDto dto=new ResponseDto();
				BeanUtils.copyProperties(employee, dto);
				dto.setDateOfBirth(employee.getDateOfBirth().toString());
				dto.setDateOfJoining(employee.getDateOfJoining().toString());
				
			emailUtils.sendMail(employee.getEmail(),"Employee Id : "+employee.getEmployeeId()+" \n "+ " Employee password "+ employee.getPassword());
			return dto;
			}else{
				emailUtils.sendMail(employee.getEmail()," your approval is Rejected contact management");		
				throw new EmployeeDataNotFound("Employee rejected");
			}
			
		}
		throw new EmployeeDataNotFound("Employee data not found with the given id");
	}

	private static String OneTimePassword(String name) {
		
		String nm=name;
        int randomIntBounded = ThreadLocalRandom.current().nextInt(1000); // From 0 to 99
		return nm+"@"+randomIntBounded;
		
	}
	
	@Override
	public ApiResponse<?> deleteAll() {
		return null;
	}

	@Override
	public ResponseDto empLogin(LoginDto dto) {
		Optional<Employee>optional=employeeRepository.findByEmployeeIdAndPassword(dto.getEmployeeIdORemail().toLowerCase(), dto.getPassword().toLowerCase());
		Optional<Employee> optional2=employeeRepository.findByEmailAndPassword(dto.getEmployeeIdORemail().toLowerCase(), dto.getPassword().toLowerCase());
		if(optional.isPresent()) {
			Employee employee=optional.get();
			ResponseDto responseDto=new ResponseDto();
			BeanUtils.copyProperties(employee, responseDto);
			responseDto.setDateOfBirth(employee.getDateOfBirth().toString());
			responseDto.setDateOfJoining(employee.getDateOfJoining().toString());
			return responseDto;
			
		}else if(optional2.isPresent()) {
			Employee employee=optional2.get();
			ResponseDto responseDto=new ResponseDto();
			BeanUtils.copyProperties(employee, responseDto);
			responseDto.setDateOfBirth(employee.getDateOfBirth().toString());
			responseDto.setDateOfJoining(employee.getDateOfJoining().toString());
			return responseDto;
		}
		
		throw new EmployeeIdAndPasswordNotFoundException("credentials are not valid");
	}

	@Override
	public ResponseDto empResetPassword(ResetPasswordDto dto) {
		Optional<Employee> optional=employeeRepository.findByEmployeeIdAndPassword(dto.getEmployeeId(), dto.getOldPassword());
		if(optional.isPresent()) {
			Employee employee=optional.get();
			employee.setPassword(dto.getNewPassword());
			employeeRepository.save(employee);
			ResponseDto responseDto=new ResponseDto();
			BeanUtils.copyProperties(employee, responseDto);
			responseDto.setDateOfBirth(employee.getDateOfBirth().toString());
			responseDto.setDateOfJoining(employee.getDateOfJoining().toString());
			return responseDto;
		}
		throw new EmployeeIdAndPasswordNotFoundException("credentials are not valid");
	}

	/*
	 * @Override public UserDetails loadUserByUsername(String username) throws
	 * UsernameNotFoundException { Optional<Employee>
	 * optional=employeeRepository.findByEmailId(username); if(optional.isEmpty()) {
	 * throw new IllegalArgumentException("Employee not found"); } Employee
	 * employee=optional.get(); User user = new User( employee.getEmailId(),
	 * employee.getPassword(), employee.getRoles().stream() .map(role -> new
	 * SimpleGrantedAuthority(role)) .collect(Collectors.toSet()) );
	 * 
	 * 
	 * return user; }
	 */
	

	@Override
	public ResponseDto getEmployeeDataByEmployeeId(String empId) {
	   Optional<Employee> optional=employeeRepository.findByEmployeeIdAndIsActive(empId,"Y");
	   if(optional.isPresent()) {
		   Employee employee=optional.get();
		   ResponseDto dto=new  ResponseDto();
		   BeanUtils.copyProperties(employee, dto);
		   dto.setEmployeeId(employee.getEmployeeId());
		   dto.setDateOfBirth(employee.getDateOfBirth().toString());
		   dto.setDateOfJoining(employee.getDateOfJoining().toString());
		   return dto;
	   }
		throw new EmployeeDataNotFound("Employee data not found with the given employee Id");

	}

	@Override
	public ResponseDto updateEmpById(String empId, EmployeeRequestDto dto) {
		Optional<Employee> optional=employeeRepository.findByEmployeeIdAndIsActive(empId,"Y");
		if(optional.isPresent()) {
			Employee employee=optional.get();
			BeanUtils.copyProperties(dto, employee);
			employeeRepository.save(employee);
			ResponseDto responseDto =new ResponseDto();
			BeanUtils.copyProperties(employee, responseDto);
			responseDto.setDateOfBirth(employee.getDateOfBirth().toString());
			responseDto.setDateOfJoining(employee.getDateOfJoining().toString());
			return responseDto;
		}
		throw new EmployeeDataNotFound("Employee data not found with the given employee Id");
	}
	
	@Override
	public ApiResponse<?> getTotalNoOfEmployees() {
		log.info("api start status ","started for getTotalNoOfEmployees");
		List<Employee> employeeList=employeeRepository.findAll();
		if(employeeList.isEmpty()){
			log.info("Exception throwing for EmployeeDataNotFound");
			throw new EmployeeDataNotFound("Employees not exist ");
		}
	 	AtomicReference<Integer> totalNoOfEmployees= new AtomicReference<>(0);
		employeeList.forEach(e->{
			if(e.getEmployeeId()!=null){
				totalNoOfEmployees.getAndSet(totalNoOfEmployees.get() + 1);
			}
		});
		log.info("Counting","TotoalNoOfEmployees");
		return new ApiResponse<>(true,"totalNoOfEmployees ",totalNoOfEmployees);
	}


	
}
