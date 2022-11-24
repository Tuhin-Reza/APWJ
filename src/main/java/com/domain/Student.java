package com.domain;

import com.service.StudentGuardianService;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "student2")
public class Student {

    @Id
    @Column(name = "student_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long student_id;

    @NotNull
    @Column(name = "student_name")
    private String student_name;

    public Long getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Long student_id) {
        this.student_id = student_id;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    @OneToOne(mappedBy = "student")
    private StudentGuardian studentGuardian;
    public StudentGuardian getStudentGuardian() {
        return studentGuardian;
    }

    public void setStudentGuardian(StudentGuardian studentGuardian) {
        this.studentGuardian = studentGuardian;
    }
}
