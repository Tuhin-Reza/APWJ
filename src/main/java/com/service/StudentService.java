package com.service;

import com.domain.Student;
import com.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

@Service
@Transactional
public class StudentService {
    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {

        this.studentRepository = studentRepository;
    }

    @Transactional
    public boolean insert(Student student)  {
        student.setStudent_name(student.getStudent_name());
        return studentRepository.create(student);
    }
    @Transactional(readOnly = true)
    public Student get(Long student_id)  {
        return studentRepository.get(student_id);
    }


}
