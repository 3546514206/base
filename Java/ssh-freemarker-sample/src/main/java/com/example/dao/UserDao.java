package com.example.dao;

import com.example.model.User;
import org.hibernate.SessionFactory;
import java.util.List;

public class UserDao {
    private SessionFactory sessionFactory;

    public void save(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    public List<User> list() {
        return sessionFactory.getCurrentSession().createQuery("from User", User.class).list();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}