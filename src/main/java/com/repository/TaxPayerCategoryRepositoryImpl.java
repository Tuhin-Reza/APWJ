package com.repository;

import com.domain.TaxPayerCategory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TaxPayerCategoryRepositoryImpl implements TaxPayerCategoryRepository{
    private SessionFactory sessionFactory;

    public TaxPayerCategoryRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<TaxPayerCategory> getAll()  {
        Session session = sessionFactory.getCurrentSession();//get the current hibernate session
        Query<TaxPayerCategory> taxPayerCategoryQuery= session.createQuery("from TaxPayerCategory",TaxPayerCategory.class);//create query
        return taxPayerCategoryQuery.getResultList();
    }
    public TaxPayerCategory create(TaxPayerCategory taxPayerCategory)  {
        Session session = sessionFactory.getCurrentSession();
        session.save(taxPayerCategory);
        return taxPayerCategory;
    }
    public TaxPayerCategory  get(Long id)  {
        Session session = sessionFactory.getCurrentSession();
        return session.get(TaxPayerCategory.class, id);
    }
    public TaxPayerCategory update(TaxPayerCategory taxPayerCategory) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(taxPayerCategory);
        return taxPayerCategory;
    }
    public void delete(Long id) {
        Session session = sessionFactory.getCurrentSession();
        TaxPayerCategory taxPayerCategory= get(id);
        if (taxPayerCategory!= null) {
            session.delete(taxPayerCategory);
        }
    }
}
