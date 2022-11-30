package com.controller;

import com.domain.Employee;
import com.service.EmployeeService;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        webDataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @RequestMapping("/create")
    public void create() {
        Employee employee = new Employee();
        employee.setFirst_name("Jerry");
        employee.setLast_name("Character");
        employee.setGender("Male");
        employee.setJoining_date(LocalDate.of(2022,11,24));
        employee.setStatus("Mouse");
        //employeeService.insert(employee);
    }

    @RequestMapping("/get")
    public void get() {
        Employee employee = employeeService.get(2L);
        System.out.println(employee.getEmployee_id() + " " + employee.getFirst_name()+ " " + employee.getLast_name()+ " " + employee.getGender()+ " " + employee.getJoining_date()+ " " + employee.getStatus());
    }
}
