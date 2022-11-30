package com.repository;

import com.domain.LeaveApplication;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LeaveApplicationRepository {
    private SessionFactory sessionFactory;

    public LeaveApplicationRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<LeaveApplication> list() {
        Session session = sessionFactory.getCurrentSession();
        Query<LeaveApplication> leaveApplicationQuery = session.createQuery("from Leave_Application", LeaveApplication.class);
        return leaveApplicationQuery.getResultList();
    }
    public List<LeaveApplication> getAll() {
        Session session = sessionFactory.getCurrentSession();
        Query<LeaveApplication> leaveApplicationQuery = session.createQuery("from LeaveApplication", LeaveApplication.class);
        return leaveApplicationQuery.getResultList();
    }

    public boolean create(LeaveApplication leaveApplication) {
        Session session = sessionFactory.getCurrentSession();
        session.save(leaveApplication);
        return true;
    }

    public LeaveApplication get(Long leave_application_id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(LeaveApplication.class,leave_application_id );
    }
    public LeaveApplication update(LeaveApplication leaveApplication) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(leaveApplication);
        return leaveApplication;
    }

    public void delete(Long leave_application_id) {
        Session session = sessionFactory.getCurrentSession();
        LeaveApplication leaveApplication = get(leave_application_id);
        if (leaveApplication != null) {
            session.delete(leaveApplication);
        }
    }


}
