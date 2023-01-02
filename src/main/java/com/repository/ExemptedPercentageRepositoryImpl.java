package com.repository;

import com.domain.ExemptedPercentage;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ExemptedPercentageRepositoryImpl implements ExemptedPercentageRepository{
    private SessionFactory sessionFactory;

    public ExemptedPercentageRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<ExemptedPercentage> getAll()  {
        Session session = sessionFactory.getCurrentSession();//get the current hibernate session
        Query<ExemptedPercentage> exemptedPercentageQuery = session.createQuery("from ExemptedPercentage ",ExemptedPercentage.class);//create query
        return exemptedPercentageQuery.getResultList();
    }
    public ExemptedPercentage create(ExemptedPercentage exemptedPercentage)  {
        Session session = sessionFactory.getCurrentSession();
        session.save(exemptedPercentage);
        return exemptedPercentage ;
    }
    public ExemptedPercentage get(Long id)  {
        Session session = sessionFactory.getCurrentSession();
        return session.get(ExemptedPercentage.class, id);
    }
    public ExemptedPercentage update(ExemptedPercentage exemptedPercentage) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(exemptedPercentage);
        return exemptedPercentage;
    }
    public void delete(Long id) {
        Session session = sessionFactory.getCurrentSession();
        ExemptedPercentage exemptedPercentage= get(id);
        if (exemptedPercentage!= null) {
            session.delete(exemptedPercentage);
        }
    }
}
