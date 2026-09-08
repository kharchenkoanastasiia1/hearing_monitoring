package com.kharchenko.spring.repository;

import com.kharchenko.spring.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DAODoctorImpl implements DAODoctor{
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Patient> getDoctorsPatients(String username) {
        Session session = sessionFactory.getCurrentSession();
        Query<Patient> query = session.createQuery("from Patient " + "where doctor_username=:userName");
        query.setParameter("userName", username);
        return query.getResultList();
    }

    @Override
    public List<Audiogram> getAudiogramPatient(String username) {
        Session session = sessionFactory.getCurrentSession();
        Query<Audiogram> query = session.createQuery("from Audiogram " + "where username=:userName");
        query.setParameter("userName", username);
        return query.getResultList();
    }

    @Override
    public Median getMedianPatient(String username) {
        Session session = sessionFactory.getCurrentSession();
        Query<Median> query = session.createQuery("from Median " + "where username=:userName" + " order by id desc");
        query.setParameter("userName", username);
        query.setMaxResults(1);
        return query.uniqueResult();
    }

    @Override
    public AgeNorm getAgeNormPatient(int age, int sex) {
        Session session = sessionFactory.getCurrentSession();
        Query<AgeNorm> query = session.createQuery("from AgeNorm " + "where age_min<=:age and age_max>:age and sex=:sex");
        query.setParameter("age", age);
        query.setParameter("sex", sex);
        return query.uniqueResult();
    }
}
