package com.kharchenko.spring.repository;

import com.kharchenko.spring.entity.Message;
import com.kharchenko.spring.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DAOMessageImpl implements DAOMessage{
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Message> getMessagesByUsername(String username) {
        Session session = sessionFactory.getCurrentSession();
        Query<Message> query = session.createQuery("from Message " + "where username=:userName");
        query.setParameter("userName", username);
        return query.getResultList();
    }

    @Override
    public void saveMessage(Message message) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(message);
    }
}
