package com.repository;

import com.domain.Student;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
public class StudentRepository {

    private SessionFactory sessionFactory;

    public StudentRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Student> list()  {
        Session session = sessionFactory.getCurrentSession();
        Query<Student> studentQuery = session.createQuery("from student2",Student.class);
        return studentQuery.getResultList();
    }

    public boolean create(Student student)  {
        Session session = sessionFactory.getCurrentSession();
        session.save(student);
        return true;
    }

    public Student get(Long student_id)  {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Student.class, student_id);
    }



}


