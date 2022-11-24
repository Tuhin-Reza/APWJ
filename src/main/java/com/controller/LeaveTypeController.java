package com.controller;

import com.domain.LeaveType;
import com.service.LeaveTypeService;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/leaveTypes")
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

    @RequestMapping("/create")
    public void create() {
        LeaveType leaveType = new LeaveType();
        leaveType.setCategory("Marriage Leave");
        leaveType.setTotal_days(20);
        leaveTypeService.insert(leaveType);
    }

    @RequestMapping("/get")
    public void get() {
        LeaveType leaveType = leaveTypeService.get(5L);
        System.out.println(leaveType.getLeave_id() + " " + leaveType.getCategory() + " " + leaveType.getTotal_days());
    }


}
