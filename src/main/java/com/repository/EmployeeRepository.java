package com.repository;


import com.domain.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeRepository {
    private SessionFactory sessionFactory;
    public  EmployeeRepository(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }
    public List<Employee> list() {
        Session session = sessionFactory.getCurrentSession();
        Query<Employee> EmployeeQuery = session.createQuery("from employee", Employee.class);
        return EmployeeQuery.getResultList();
    }

    public boolean create(Employee employee) {
        Session session = sessionFactory.getCurrentSession();
        session.save(employee);
        return true;
    }

    public Employee get(Long employee_id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Employee.class, employee_id);
    }
}
