package com.kharchenko.spring.repository;

import com.kharchenko.spring.entity.Authorities;
import com.kharchenko.spring.entity.Doctor;
import com.kharchenko.spring.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DAOUserImpl implements DAOUser{
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<User> getAlUsers() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from User", User.class).getResultList();
    }

    @Override
    public User getUser(int id){
        Session session = sessionFactory.getCurrentSession();
        return session.get(User.class, id);
    }

    @Override
    public User getUserByUsername(String username){
        Session session = sessionFactory.getCurrentSession();
        Query<User> query = session.createQuery("from User " + "where username=:userName");
        query.setParameter("userName", username);
        return query.uniqueResult();
    }

    @Override
    public void saveUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(user);
    }

    @Override
    public void deleteUser(String username) {
        Session session = sessionFactory.getCurrentSession();
        Query<User> query = session.createQuery("delete from User " + "where username=:userName");
        query.setParameter("userName", username);
        query.executeUpdate();
    }

    @Override
    public void saveAuthorities(Authorities authorities) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(authorities);
    }

    @Override
    public void deleteAuthorities(String username) {
        Session session = sessionFactory.getCurrentSession();
        Query<Authorities> query = session.createQuery("delete from Authorities " + "where username=:userName");
        query.setParameter("userName", username);
        query.executeUpdate();
    }
}
