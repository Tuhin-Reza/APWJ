package com.repository;

import com.domain.TaxRatePayCat;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TaxRatePayCatRepositoryImpl implements TaxRatePayCatRepository{
    private SessionFactory sessionFactory;

    public TaxRatePayCatRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<TaxRatePayCat> getAll()  {
        Session session = sessionFactory.getCurrentSession();//get the current hibernate session
        Query<TaxRatePayCat> taxRatePayCatQuery = session.createQuery("from TaxRatePayCat ",TaxRatePayCat.class);//create query
        return taxRatePayCatQuery.getResultList();
    }
    public TaxRatePayCat create(TaxRatePayCat taxRatePayCat)  {
        Session session = sessionFactory.getCurrentSession();
        session.save(taxRatePayCat);
        return taxRatePayCat;
    }
    public TaxRatePayCat get(Long id)  {
        Session session = sessionFactory.getCurrentSession();
        return session.get(TaxRatePayCat.class, id);
    }
    public TaxRatePayCat update(TaxRatePayCat taxRatePayCat) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(taxRatePayCat);
        return taxRatePayCat;
    }
    public void delete(Long id) {
        Session session = sessionFactory.getCurrentSession();
        TaxRatePayCat taxRatePayCat= get(id);
        if (taxRatePayCat!= null) {
            session.delete(taxRatePayCat);
        }
    }
}
