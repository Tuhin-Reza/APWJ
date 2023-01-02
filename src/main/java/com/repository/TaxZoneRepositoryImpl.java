package com.repository;

import com.domain.TaxZone;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TaxZoneRepositoryImpl implements TaxZoneRepository{
    private SessionFactory sessionFactory;

    public TaxZoneRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<TaxZone> getAll()  {
        Session session = sessionFactory.getCurrentSession();//get the current hibernate session
        Query<TaxZone> taxZoneQuery= session.createQuery("from TaxZone",TaxZone.class);//create query
        return taxZoneQuery.getResultList();
    }
    public TaxZone create(TaxZone taxZone)  {
        Session session = sessionFactory.getCurrentSession();
        session.save(taxZone);
        return taxZone;
    }
    public TaxZone get(Long id)  {
        Session session = sessionFactory.getCurrentSession();
        return session.get(TaxZone.class, id);
    }
    public TaxZone update(TaxZone taxZone) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(taxZone);
        return taxZone;
    }
    public void delete(Long id) {
        Session session = sessionFactory.getCurrentSession();
        TaxZone taxZone= get(id);
        if (taxZone!= null) {
            session.delete(taxZone);
        }
    }

}
