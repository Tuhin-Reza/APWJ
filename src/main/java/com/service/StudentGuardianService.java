package com.service;

import com.domain.StudentGuardian;
import com.repository.StudentGuardianRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StudentGuardianService {
    private StudentGuardianRepository studentGuardianRepository;
    public StudentGuardianService(StudentGuardianRepository studentGuardianRepository) {
        this.studentGuardianRepository = studentGuardianRepository;
    }
    @Transactional
    public boolean insert(StudentGuardian studentGuardian) {
        studentGuardian.setFatherName(studentGuardian.getFatherName());
        studentGuardian.setMatherName(studentGuardian.getMatherName());
        return studentGuardianRepository.create(studentGuardian);
    }

    @Transactional(readOnly = true)
    public StudentGuardian get(Long student_guardian_id) {

        return studentGuardianRepository.get(student_guardian_id);
    }
}
