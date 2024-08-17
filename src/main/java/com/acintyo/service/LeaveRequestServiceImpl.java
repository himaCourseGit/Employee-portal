package com.acintyo.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acintyo.Entity.CreditLeaves;
import com.acintyo.Entity.DebitLeaves;
import com.acintyo.Entity.Employee;
import com.acintyo.Entity.LeaveRequest;
import com.acintyo.customexceptions.EmployeeDataNotFound;
import com.acintyo.customexceptions.LeaveRequestNotFound;
import com.acintyo.dto.ApiResponse;
import com.acintyo.dto.CreditLeaveRequestDto;
import com.acintyo.dto.DebitLeaveRequestDto;
import com.acintyo.dto.EmployeeRequestDto;
import com.acintyo.dto.LeaveRequestDto;
import com.acintyo.dto.LeaveResponseDto;
import com.acintyo.dto.MailBody;
import com.acintyo.dto.MailBody.MailBodyBuilder;
import com.acintyo.repository.ICreditLeavesRepository;
import com.acintyo.repository.IDebitLeavesRepository;
import com.acintyo.repository.IEmployeeRepository;
import com.acintyo.repository.ILeaveRequestRepository;
import com.acintyo.utils.EmailUtils;

@Service
public class LeaveRequestServiceImpl implements ILeaveRequestService {
	
	@Autowired
	private ILeaveRequestRepository repository;
	
	@Autowired
	private IEmployeeRepository employeeRepository;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private EmailUtils emailUtils;
	
	@Autowired
	private IDebitLeavesRepository debitrepository;
	
	@Autowired
	private DebitLeavesServiceImpl debitLeavesServiceImpl;
	
	@Autowired
	private ICreditLeavesRepository creditLeavesRepository;
	

	@Override
	public ApiResponse<?> postingOfLeave(LeaveRequestDto dto) {
		
		Optional<Employee> optional=employeeRepository.findByEmployeeIdAndIsActive( dto.getEmployeeId(),"Y");
		if(optional.isPresent()) {
		Employee employee=optional.get();
		 // Convert fromDate and toDate from dto to LocalDate
	    LocalDate fromDate = dto.getFromDate();
	    LocalDate toDate = dto.getToDate();

	    // Calculate the difference in days
	    long calculatedNumberOfDays = ChronoUnit.DAYS.between(fromDate, toDate) + 1; // including the fromDate in the count

	    // Check if the calculated number of days matches the provided number of days
	    if (calculatedNumberOfDays != dto.getRequestDays()) {
	        return new ApiResponse<>(false, "The number of days does not match the period between fromDate and toDate");
	    }	
		LeaveRequest request = new LeaveRequest();
		request.setEmployeeId(dto.getEmployeeId());
		request.setFromDate(dto.getFromDate());
		request.setToDate(dto.getToDate());
		request.setRequestDays(dto.getRequestDays());
		request.setLeaveType(dto.getLeaveType());
		request.setDescription(dto.getDescription());
		request.setStatusByAdmin("P");
		request.setStatusByUser("R");
		request.setIsHalfDayLeave(dto.getIsHalfDayLeave());
		request=repository.save(request);
		ApiResponse<?> response=new ApiResponse<>(true, "Employee Leave posted",List.of());
		emailUtils.sendLeavePostMails(employee.getEmail(),"leave request has been sent successfully........"+request.getId());
		return response;
		}
		throw new EmployeeDataNotFound("Employee not found with given id");
	}

	@Override
	public ApiResponse<?> findBYApproved(String status, Pageable pageable) {
		Page<LeaveRequest> page =repository.findByStatusByAdmin(status, pageable);
		if(page.isEmpty()) {
			return new ApiResponse<>(true, "Data not found", new PageImpl<>(List.of(), page.getPageable(), page.getTotalElements()));
		}
		List<LeaveRequest> content = page.getContent();	
		List<LeaveResponseDto> responseList = new ArrayList<>();
		content.forEach(entity->{
			LeaveResponseDto responseDto = new LeaveResponseDto();
			responseDto.setDescription(entity.getDescription());
			responseDto.setEmployeeId(entity.getEmployeeId());
			responseDto.setFromDate(entity.getFromDate().toString());
			responseDto.setId(entity.getId());
			responseDto.setLeaveType(entity.getLeaveType());
			responseDto.setRequestDays(entity.getRequestDays().toString());
			responseDto.setToDate(entity.getToDate().toString());
			responseDto.setStatusByAdmin(entity.getStatusByAdmin());
			responseDto.setStatusByUser(entity.getStatusByUser());
			responseDto.setIsHalfDayLeave(entity.getIsHalfDayLeave());
			responseList.add(responseDto);
			
		});
		return new ApiResponse<>(true, "Data fetched successfully", 
				new PageImpl<>(responseList, page.getPageable(), page.getTotalElements()));
	}
	

	

	@Override
	public ApiResponse<?> updateLeaveRequest(Integer id, LeaveRequestDto dto) {
		Optional<Employee> opt=employeeRepository.findByEmployeeIdAndIsActive(dto.getEmployeeId(),"Y");
		if(opt.isPresent()){
			Employee employee=opt.get();
		Optional<LeaveRequest> optional=repository.findById(id);
		if(optional.isPresent())
		{
			LeaveRequest request=new LeaveRequest();
			request.setEmployeeId(dto.getEmployeeId());
			request.setDescription(dto.getDescription());
			request.setFromDate(dto.getFromDate());
			request.setToDate(dto.getToDate());
			request.setLeaveType(dto.getLeaveType());
			request.setRequestDays(dto.getRequestDays());
			request.setIsHalfDayLeave(dto.getIsHalfDayLeave());
			request.setStatusByUser(dto.getStatusByUser());
			request.setStatusByAdmin(dto.getStatusByAdmin());
			request=repository.save(request);
			emailUtils.sendLeavePostMails(employee.getEmail(), "leave request has been updated successfully........");
			return new ApiResponse<>(true, "Data updated sucessfully");
		}
		throw new LeaveRequestNotFound("leaveRequest id not found");
		}
		throw new EmployeeDataNotFound("emp not found with the given empId");
		
	}
	
//	@Transactional
//	@Override
//	public ApiResponse<?> updateByApproved(Integer id, String approved) {
//		 Optional<LeaveRequest> exist=repository.findById(id);
//		 if(exist.isEmpty()) {
//			 throw new LeaveRequestNotFound("Leave request not found");
//		 }
//		 LeaveRequest leaveRequest=exist.get();
//		 Optional<Employee> optional=employeeRepository.findByEmployeeIdAndIsActive(leaveRequest.getEmployeeId(),"Y");
//		 if(optional.isPresent()) {
//			 Employee employee=optional.get();		 			 
//			 repository.updateStatusByAdmin(id, approved);
//			 if(approved.equalsIgnoreCase("A")) {
//				 emailUtils.sendLeaveAcceptMail(employee.getEmail(), "Leave status approved");
//				 return new ApiResponse<>(true,"Leave status approved");
//			 }else {
//				 emailUtils.sendLeaveAcceptMail(employee.getEmailId(), "Leave status rejected");
//				 return new ApiResponse<>(true,"Leave status rejected");
//				 
//			 }
//		 }
//		 throw new EmployeeDataNotFound("emp not found with the given Id");
//	}
	
	@Transactional
@Override
public ApiResponse<?> updateByApproved(Integer id, String approved,String employeeId,String approvedBy) {
	 Optional<LeaveRequest> exist=repository.findById(id);
	 
	 if(exist.isEmpty()) {
			 throw new LeaveRequestNotFound("Leave request not found");
	 }
	 
	 repository.updateStatusByAdmin(id, approved,approvedBy);
	 
	 if(approved.equalsIgnoreCase("A")) {
		 MailBody mailbody=MailBody.builder().subject("Leave request status").text("your Leave Request is approoved "+id).to(employeeRepository.findByEmployeeId(employeeId).get().getEmail()).build();
	emailService.sendSimpleMessage(mailbody);
		 LeaveRequest leaveRequest = exist.get(); 
		 Object response= debitLeavesServiceImpl.updateByDebitLeaves(id, employeeId, leaveRequest.getRequestDays()).getData();
		DebitLeaves debitleaves=(DebitLeaves) response;
		debitleaves.setLeaveType(leaveRequest.getLeaveType());
	debitleaves.setEmployeeId(leaveRequest.getEmployeeId());
			debitleaves=debitrepository.save(debitleaves); 
		return new ApiResponse<>(true,"Leave status approved");
		 }else {
	MailBody mailbody=MailBody.builder().subject("Leave request status").text("Sorry for your leave request,Your leave request is rejected"+id).to(employeeRepository.findByEmployeeId(employeeId).get().getEmail()).build();
			 return new ApiResponse<>(true,"Leave status rejected");		 
		 }
	}

	

	 @Override
	 	public ResponseEntity<?> compOffRequest(LeaveRequestDto dto)
	 	{
	 		LeaveRequest request=new LeaveRequest();
	 		request.setFromDate(dto.getFromDate());
	 		request.setToDate(dto.getToDate());
	 		request.setLeaveType(dto.getLeaveType());
	 		request.setDescription(dto.getDescription());
	 		request.setRequestDays(dto.getRequestDays());
	 		request.setCreatedDate(LocalDateTime.now());
	 		request.setEmployeeId(dto.getEmployeeId());
	 		request.setStatusByAdmin("P");
	 		request.setStatusByUser("R");
	 		request=repository.save(request);
	 		MailBody mailbody=MailBody.builder().subject("CompOff Requested").text("Your CompOff leave request posted").to(employeeRepository.findByEmployeeId(request.getEmployeeId()).get().getEmail()).build();
	 		emailService.sendSimpleMessage(mailbody);
	 		return new ResponseEntity<>(new ApiResponse<>(true,"CompOff leave request raised successfully"), HttpStatus.OK);
	 	}

	@Override
	public ApiResponse<?> compOffApproval(Integer id, String statusByAdmin) {
		Optional<LeaveRequest> optionalLeaveRequest = repository.findById(id);
		if(optionalLeaveRequest.isPresent()) {
			if(optionalLeaveRequest.get().getLeaveType().equalsIgnoreCase("Compoff")) {
			LeaveRequest leaveRequest = optionalLeaveRequest.get();
			leaveRequest.setStatusByAdmin(statusByAdmin);
			leaveRequest =repository.save(leaveRequest);
			if(statusByAdmin.equalsIgnoreCase("A")) {
				CreditLeaves creditleves=new CreditLeaves();
				creditleves.setCreditLeaves(leaveRequest.getRequestDays());
				creditleves.setEmployeeId(leaveRequest.getEmployeeId());
				creditleves.setLeaveType(leaveRequest.getLeaveType());
				creditleves.setMonth(LocalDate.now());
				creditLeavesRepository.save(creditleves);
				MailBody mailBody=MailBody.builder().subject("CompOff approval").text("Your CompOff request approved").to(employeeRepository.findByEmployeeId(leaveRequest.getEmployeeId()).get().getEmail()).build();
				emailService.sendSimpleMessage(mailBody);
				return new ApiResponse<>(true,"Compoff approved successfully,please check your mail");
			}	
			else {
				MailBody mailBody=MailBody.builder().subject("CompOff approval").text("Your CompOff leave request rejected").to(employeeRepository.findByEmployeeId(leaveRequest.getEmployeeId()).get().getEmail()).build();
				emailService.sendSimpleMessage(mailBody);	
			}
			}
		}
		return new ApiResponse<>(false,"Compoff rejected successfully,sorry for Your leaveRequest");
	}
//	@Override
//	public ApiResponse<?> leaveRequestApprovalPreferrence(Integer id,Double requestDays, CreditLeaveRequestDto creditdto,
//			DebitLeaveRequestDto debitdto,String employeeId) {
//		Optional<LeaveRequest> preferleaveRequest=repository.findById(id);
//		if(preferleaveRequest.isPresent()) {
//			preferleaveRequest.get().getRequestDays();
//			List<CreditLeaves> creditleaves=creditLeavesRepository.findByEmployeeId(employeeId);
//			creditleaves.set(dt)
//	}
//		return null;	
//	}
	
	
}

