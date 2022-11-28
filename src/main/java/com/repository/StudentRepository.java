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
        Session session = sessionFactory.getCurrentSession();//get the current hibernate session
        Query<Student> studentQuery = session.createQuery("from student",Student.class);//create query
        List<Student> student=studentQuery.getResultList();
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
    public List<Student> getAll() {
        Session session = sessionFactory.getCurrentSession();
        Query<Student> studentQuery = session.createQuery("from Student", Student.class);
        return studentQuery.getResultList();
    }

    public Student update(Student student) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(student);
        return student;
    }

    public void delete(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Student student = get(id);
        if (student!= null) {
            session.delete(student);
        }
    }

}


