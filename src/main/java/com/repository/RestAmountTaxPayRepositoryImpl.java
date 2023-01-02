package com.repository;

import com.domain.RestAmountTaxPay;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RestAmountTaxPayRepositoryImpl implements RestAmountTaxPayRepository{
    private SessionFactory sessionFactory;

    public RestAmountTaxPayRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<RestAmountTaxPay> getAll()  {
        Session session = sessionFactory.getCurrentSession();//get the current hibernate session
        Query<RestAmountTaxPay> restAmountTaxPayQuery = session.createQuery("from RestAmountTaxPay",RestAmountTaxPay.class);//create query
        return restAmountTaxPayQuery.getResultList();
    }
    public RestAmountTaxPay create(RestAmountTaxPay restAmountTaxPay)  {
        Session session = sessionFactory.getCurrentSession();
        session.save(restAmountTaxPay);
        return restAmountTaxPay;
    }
    public RestAmountTaxPay get(Long id)  {
        Session session = sessionFactory.getCurrentSession();
        return session.get(RestAmountTaxPay.class, id);
    }
    public RestAmountTaxPay update(RestAmountTaxPay restAmountTaxPay) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(restAmountTaxPay);
        return restAmountTaxPay;
    }
    public void delete(Long id) {
        Session session = sessionFactory.getCurrentSession();
        RestAmountTaxPay restAmountTaxPay= get(id);
        if (restAmountTaxPay!= null) {
            session.delete(restAmountTaxPay);
        }
    }
}
