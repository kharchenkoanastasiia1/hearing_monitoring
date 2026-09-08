package com.kharchenko.spring.repository;

import com.kharchenko.spring.entity.Audiogram;
import com.kharchenko.spring.entity.Doctor;
import com.kharchenko.spring.entity.Patient;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DAOImpl implements DAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Doctor> getAllDoctors() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Doctor", Doctor.class).getResultList();
    }

    @Override
    public void saveDoctor(Doctor doctor) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(doctor);
    }

    @Override
    public Doctor getDoctor(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Doctor.class, id);
    }

    @Override
    public Doctor getDoctor(String username) {
        Session session = sessionFactory.getCurrentSession();
        Query<Doctor> query = session.createQuery("from Doctor " + "where username=:userName");
        query.setParameter("userName", username);
        return query.uniqueResult();
    }

    @Override
    public void deleteDoctor(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query<Doctor> query = session.createQuery("delete from Doctor " + "where id=:doctorId");
        query.setParameter("doctorId", id);
        query.executeUpdate();
    }

    @Override
    public List<Patient> getAllPatients() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Patient", Patient.class).getResultList();
    }

    @Override
    public void savePatient(Patient patient) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(patient);
    }

    @Override
    public Patient getPatient(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Patient.class, id);
    }

    @Override
    public Patient getPatient(String username) {
        Session session = sessionFactory.getCurrentSession();
        Query<Patient> query = session.createQuery("from Patient " + "where username=:userName");
        query.setParameter("userName", username);
        return query.uniqueResult();
    }

    @Override
    public void deletePatient(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query<Doctor> query = session.createQuery("delete from Patient " + "where id=:patientId");
        query.setParameter("patientId", id);
        query.executeUpdate();
    }
}
