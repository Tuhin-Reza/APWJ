package com.repository;

import com.domain.ExemptedPercentage;
import com.domain.TaxHistory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class TaxHistoryRepositoryImpl implements TaxHistoryRepository {
    private SessionFactory sessionFactory;

    public TaxHistoryRepositoryImpl(SessionFactory sessionFactory) {

        this.sessionFactory = sessionFactory;
    }

    public List<TaxHistory> getAll()  {
        Session session = sessionFactory.getCurrentSession();//get the current hibernate session
        Query<TaxHistory> taxHistoryQuery = session.createQuery("from TaxHistory", TaxHistory.class);//create query
        return taxHistoryQuery.getResultList();
    }
    public TaxHistory create(TaxHistory taxHistory)  {
        Session session = sessionFactory.getCurrentSession();
        session.save(taxHistory);
        return taxHistory;
    }
    public TaxHistory get(Long id)  {
        Session session = sessionFactory.getCurrentSession();
        return session.get(TaxHistory.class, id);
    }

    public void delete(Long id) {
        Session session = sessionFactory.getCurrentSession();
       TaxHistory taxHistory= get(id);
        if (taxHistory!= null) {
            session.delete(taxHistory);
        }
    }
}
