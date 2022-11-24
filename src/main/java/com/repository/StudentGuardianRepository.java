package com.repository;

import com.domain.StudentGuardian;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentGuardianRepository {

    private SessionFactory sessionFactory;

    public StudentGuardianRepository(SessionFactory sessionFactory) {

        this.sessionFactory = sessionFactory;
    }

    public List<StudentGuardian> list() {
        Session session = sessionFactory.getCurrentSession();
        Query<StudentGuardian> studentGuardianQuery = session.createQuery("from student_guardian", StudentGuardian.class);
        return studentGuardianQuery.getResultList();
    }

    public boolean create(StudentGuardian studentGuardian) {
        Session session = sessionFactory.getCurrentSession();
        session.save(studentGuardian);
        return true;
    }

    public StudentGuardian get(Long student_guardian_id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(StudentGuardian.class, student_guardian_id);
    }

}
