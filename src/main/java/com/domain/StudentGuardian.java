package com.domain;

import javax.persistence.*;

@Entity
@Table(name = "student_guardian")
public class StudentGuardian {
    @Id
    @Column(name = "student_guardian_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long student_guardian_id;

    @Column(name = "father_name")
    private String fatherName;

    @Column(name = "mather_name")
    private String matherName;

    public Long getStudent_guardian_id() {
        return student_guardian_id;
    }

    public void setStudent_guardian_id(Long student_guardian_id) {
        this.student_guardian_id = student_guardian_id;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getMatherName() {
        return matherName;
    }

    public void setMatherName(String motherName) {
        this.matherName = motherName;
    }

    @OneToOne
    @JoinColumn(name = "student_id")
    private Student student;
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }


}
