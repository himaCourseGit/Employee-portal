package com.acintyo.component;

import com.acintyo.Entity.CreditLeaves;
import com.acintyo.Entity.Employee;
import com.acintyo.repository.ICreditLeavesRepository;
import com.acintyo.repository.IEmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@Slf4j
public class EarnedLeaveScheduledComponent
{

    @Autowired
    ICreditLeavesRepository iCreditLeavesRepository;

    @Autowired
    IEmployeeRepository employeeRepository;



    @Scheduled(cron = "0 0 0 1 * *")
    public void earnedLeaveScheduled()
    {
        List<Employee> employeeList=employeeRepository.findAll();
        // System.out.println(employeeList);
        employeeList.forEach(e->{
            if(e.getEmployeeId()!=null){
                CreditLeaves creditLeaves=new CreditLeaves();
                creditLeaves.setCreditLeaves(1.0);
                creditLeaves.setLeaveType("EarnedLeave");
                creditLeaves.setMonth(LocalDate.now());
                creditLeaves.setEmployeeId(e.getEmployeeId());
                iCreditLeavesRepository.save(creditLeaves);
                log.info("Earned Leaves added " +e.getEmployeeId() +" Month :"+LocalDate.now()+ "successfully");
            }
        });
    }

}
