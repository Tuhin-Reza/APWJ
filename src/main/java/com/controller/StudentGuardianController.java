package com.controller;

import com.domain.Student;
import com.domain.StudentGuardian;
import com.service.StudentGuardianService;
import com.service.StudentService;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/student_guardians")
public class StudentGuardianController {

    private StudentGuardianService studentGuardianService;
    private StudentService studentService;

    public StudentGuardianController(StudentGuardianService studentGuardianService, StudentService studentService) {
        this.studentGuardianService = studentGuardianService;
        this.studentService = studentService;
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        webDataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @RequestMapping("/create")
    public void create() {
        Student student = studentService.get(1L);
        StudentGuardian studentGuardian = new StudentGuardian();
        studentGuardian.setFatherName("William Hanna");
        studentGuardian.setMatherName("William Hanna");
        studentGuardian.setStudent(student);
        //studentGuardianService.insert(studentGuardian);
    }
    @RequestMapping("/get")
    public void get() {
        StudentGuardian studentGuardian = studentGuardianService.get(1L);
        System.out.println(studentGuardian.getStudent_guardian_id() + " " + studentGuardian.getStudent().getStudent_name()+" "+ studentGuardian.getFatherName() + " " + studentGuardian.getMatherName());
        //System.out.println(studentGuardian.getStudent().getFirstname() + " " + studentGuardian.getStudent().getLastname());
    }





}
