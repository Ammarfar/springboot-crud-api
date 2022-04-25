package com.example.demo.repository;

import java.util.List;

import javax.persistence.EntityManager;

import com.example.demo.model.User;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private EntityManager entityManager;

    @Autowired
    public UserRepositoryImpl(EntityManager em) {
        entityManager = em;
    }

    @Override
    @Transactional
    public List<User> findAll() {
        Session s = entityManager.unwrap(Session.class);

        Query<User> query = s.createQuery("from User", User.class);

        List<User> users = query.getResultList();

        return users;
    }

    @Override
    @Transactional
    public User findById(int id) {
        Session s = entityManager.unwrap(Session.class);

        User user = s.get(User.class, id);

        return user;
    }

    @Override
    @Transactional
    public void create(User user) {
        Session s = entityManager.unwrap(Session.class);

        s.saveOrUpdate(user);
    }
}
