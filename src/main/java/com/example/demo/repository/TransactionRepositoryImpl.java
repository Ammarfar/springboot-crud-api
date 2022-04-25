package com.example.demo.repository;

import java.text.MessageFormat;
import java.util.List;

import javax.persistence.EntityManager;

import com.example.demo.model.Transaction;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class TransactionRepositoryImpl implements TransactionRepository {
    private EntityManager entityManager;

    @Autowired
    public TransactionRepositoryImpl(EntityManager em) {
        entityManager = em;
    }

    @Override
    @Transactional
    public void create(Transaction transactions) {
        Session s = entityManager.unwrap(Session.class);

        s.saveOrUpdate(transactions);
    }

    @Override
    @Transactional
    public List<Transaction> findAll(String from, String to, Integer userId) {
        Session s = entityManager.unwrap(Session.class);

        String w = "";
        if (userId != null)
            w = w.concat("AND userid = '" + userId + "'");
        if (from != null)
            w = w.concat(" AND trxdate >= '" + from + "'");
        if (to != null)
            w = w.concat(" AND trxdate <= '" + to + "'");

        String q = MessageFormat.format("FROM transactions WHERE userid IS NOT NULL {0} ORDER BY trxdate desc", w);

        Query<Transaction> query = s.createQuery(q, Transaction.class);

        return query.getResultList();
    }

}
