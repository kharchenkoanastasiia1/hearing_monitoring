package com.kharchenko.spring.repository;

import com.kharchenko.spring.entity.Audiogram;
import com.kharchenko.spring.entity.Median;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Repository
public class DAOPatientImpl implements DAOPatient{
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void saveAudiogram(Audiogram audiogram) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(audiogram);
    }

    @Override
    public void saveMedian(Median median) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(median);
    }

    @Override
    public Date getAudiogramDate(String username) throws ParseException {
        Session session = sessionFactory.getCurrentSession();
        Query<Audiogram> query = session.createQuery("from Audiogram " + "where username=:userName" + " order by id desc");
        query.setParameter("userName", username);
        query.setMaxResults(1);
        Audiogram audiogram = query.uniqueResult();
        if(audiogram == null){
            return null;
        }
        return audiogram.getDate();
    }

    @Override
    public Date getMedianDate(String username) throws ParseException {
        Session session = sessionFactory.getCurrentSession();
        Query<Median> query = session.createQuery("from Median " + "where username=:userName" + " order by id desc");
        query.setParameter("userName", username);
        query.setMaxResults(1);
        Median median = query.uniqueResult();
        if(median == null){
            return null;
        }
        return median.getDate();
    }



//    @Override
//    public void saveAudiogramDate(Audiogram audiogram) {
////        Session session = sessionFactory.getCurrentSession();
////        String hql = "INSERT INTO Audiogram (username, f125_left, f250_left, f500_left, f1000_left, f2000_left, f3000_left, f4000_left, f8000_left, f125_right, f250_right, f500_right, f1000_right, f2000_right, f3000_right, f4000_right, f8000_right, date) " +
////                "select :userName, :f125_left, :f250_left, :f500_left, :f1000_left, :f2000_left, :f3000_left, :f4000_left, :f8000_left, :f125_right, :f250_right, :f500_right, :f1000_right, :f2000_right, :f3000_right, :f4000_right, :f8000_right, :date";
////
////        Query query = session.createQuery(hql);
////        query.setParameter("userName", audiogram.getUsername());
////        query.setParameter("f125_left", audiogram.getF125Left());
////        query.setParameter("f250_left", audiogram.getF250Left());
////        query.setParameter("f500_left", audiogram.getF500Left());
////        query.setParameter("f1000_left", audiogram.getF1000Left());
////        query.setParameter("f2000_left", audiogram.getF2000Left());
////        query.setParameter("f3000_left", audiogram.getF3000Left());
////        query.setParameter("f4000_left", audiogram.getF4000Left());
////        query.setParameter("f8000_left", audiogram.getF8000Left());
////        query.setParameter("f125_right", audiogram.getF125Right());
////        query.setParameter("f250_right", audiogram.getF250Right());
////        query.setParameter("f500_right", audiogram.getF500Right());
////        query.setParameter("f1000_right", audiogram.getF1000Right());
////        query.setParameter("f2000_right", audiogram.getF2000Right());
////        query.setParameter("f3000_right", audiogram.getF3000Right());
////        query.setParameter("f4000_right", audiogram.getF4000Right());
////        query.setParameter("f8000_right", audiogram.getF8000Right());
////        query.setParameter("date", audiogram.getSqlDate());
////        query.executeUpdate();
//    }
}
