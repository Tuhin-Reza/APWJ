package com.controller;

import com.domain.LeaveApplication;
import com.domain.LeaveType;
import com.exception.BadRequestAlertException;
import com.service.LeaveTypeService;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
public class LeaveTypeController {
    private LeaveTypeService leaveTypeService;

    public LeaveTypeController(LeaveTypeService leaveTypeService) {
        this.leaveTypeService = leaveTypeService;
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        webDataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @RequestMapping("/get")
    public LeaveType fetch() {
        LeaveType leaveType = leaveTypeService.get(1L);
       // System.out.println(leaveType.getLeave_id() + " " + leaveType.getCategory() + " " + leaveType.getTotal_days());
        return leaveType;
    }
    @GetMapping("/getAll-leave-type")
    public ResponseEntity<List<LeaveType>> getAllLeaveApplications() {
        List<LeaveType> LeaveType = leaveTypeService.getAll();
        return ResponseEntity.ok().body(LeaveType);
    }
    @PostMapping("/create-leave-type")
    public ResponseEntity<LeaveType> createLeaveType(@Valid @RequestBody LeaveType leaveType) throws Exception {
        if (leaveType.getLeave_id() != null) {
            throw new BadRequestAlertException("A new leave application cannot have an id value");
        }
        leaveTypeService.insert(leaveType);
        return ResponseEntity.created(new URI("/create-leave-applications/"))
                .body(leaveType);
    }
    @RequestMapping(method= RequestMethod.POST, value="/leave-type")
    public void addEmployee(@RequestBody LeaveType leaveType){

        leaveTypeService.insert(leaveType);
    }
    @RequestMapping(value = "/user/", method = RequestMethod.POST)
    public ResponseEntity<Void> createUser(@RequestBody LeaveType user,    UriComponentsBuilder ucBuilder) {
        System.out.println("Creating User " + user.getCategory());

//        if (leaveTypeService.isUserExist(user)) {
//            System.out.println("A User with name " + user.getName() + " already exist");
//            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
//        }

        leaveTypeService.insert(user);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getLeave_id()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }


}
