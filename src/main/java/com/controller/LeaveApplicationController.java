package com.controller;

import com.domain.Employee;
import com.domain.LeaveApplication;
import com.domain.LeaveType;
import com.service.EmployeeService;
import com.service.LeaveApplicationService;
import com.service.LeaveTypeService;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;

@Controller
@RequestMapping("/leaveApplications")
public class LeaveApplicationController {
    private LeaveApplicationService leaveApplicationService;

    private LeaveTypeService leaveTypeService;

    private EmployeeService employeeService;

    public LeaveApplicationController(LeaveApplicationService leaveApplicationService, LeaveTypeService leaveTypeService, EmployeeService employeeService) {
        this.leaveApplicationService = leaveApplicationService;
        this.leaveTypeService = leaveTypeService;
        this.employeeService = employeeService;
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        webDataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @RequestMapping("/create")
    public void create() throws Exception {
        LeaveType leaveType = leaveTypeService.get(1L);
        Employee employee=employeeService.get(1l);

        LeaveApplication leaveApplication = new LeaveApplication();
        leaveApplication.setLeaveType(leaveType);
        leaveApplication.setEmployee(employee);

        leaveApplication.setForm_date(LocalDate.of(2022, 11, 25));
        leaveApplication.setTo_date(LocalDate.of(2022, 11, 30));
        leaveApplication.setTotal_days(5);
        leaveApplication.setReason("High Fever");
        boolean result = leaveApplicationService.insert(leaveApplication);
        if (!result) {
            throw new Exception("Constraint Violation");
        }
    }

    @RequestMapping("/get")
    public void get() {
        LeaveApplication leaveApplication = leaveApplicationService.get(1L);
        System.out.println(leaveApplication.getLeave_application_id() + " " + leaveApplication.getEmployee().getFirst_name()+"  " +leaveApplication.getEmployee().getLast_name()+ " "+ leaveApplication.getLeaveType().getCategory() +" "+leaveApplication.getTotal_days() + " " + leaveApplication.getReason());
    }


}
