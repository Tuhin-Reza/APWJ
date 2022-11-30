package com.controller;

import com.domain.Employee;
import com.domain.LeaveApplication;
import com.domain.LeaveType;
import com.exception.BadRequestAlertException;
import com.exception.NotFoundAlertException;
import com.service.EmployeeService;
import com.service.LeaveApplicationService;
import com.service.LeaveTypeService;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api-leaveApplications")
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
    @PostMapping("/create-leave-application")
    public ResponseEntity<LeaveApplication> createLeaveApplication(@Valid @RequestBody LeaveApplication leaveApplication) throws Exception {
        if (leaveApplication.getLeave_application_id() != null) {
            throw new BadRequestAlertException("A new leave application cannot have an id value");
        }
        leaveApplicationService.insert(leaveApplication);
        return ResponseEntity.created(new URI("/create-leave-application/"))
                .body(leaveApplication);
    }
    @GetMapping("/getOne-leave-application/{id}")
    public ResponseEntity<LeaveApplication> getLeaveApplication(@PathVariable Long id) {
        Optional<LeaveApplication> leaveApplication = Optional.ofNullable(leaveApplicationService.get(id));
        if (leaveApplication.isPresent()) {
            return ResponseEntity.ok().body(leaveApplication.get());
        }
        throw new NotFoundAlertException("Record not found [" + id + "]");
    }
    @GetMapping("/getAll-leave-applications")
    public ResponseEntity<List<LeaveApplication>> getAllLeaveApplications() {
        List<LeaveApplication> leaveApplications = leaveApplicationService.getAll();
        return ResponseEntity.ok().body(leaveApplications);
    }

    @PutMapping("/update-leave-application")
    public ResponseEntity<LeaveApplication> updateLeaveApplication(@Valid @RequestBody LeaveApplication leaveApplication) throws Exception {
        if (leaveApplication.getLeave_application_id() == null) {
            throw new BadRequestAlertException("Invalid Id");
        }
        leaveApplicationService.update(leaveApplication);
        return ResponseEntity.created(new URI("/update-leave-applications/"))
                .body(leaveApplication);
    }
    @DeleteMapping("/delete-leave-application/{id}")
    public ResponseEntity<LeaveApplication> deleteLeaveApplication(@PathVariable Long id) {
        leaveApplicationService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
