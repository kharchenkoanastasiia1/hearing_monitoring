package com.kharchenko.spring.service;

import com.kharchenko.spring.entity.Doctor;
import com.kharchenko.spring.entity.Patient;
import com.kharchenko.spring.repository.DAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@org.springframework.stereotype.Service
@Transactional
public class ServiceImpl implements Service {
    @Autowired
    private DAO dao;

    @Override
    @Transactional
    public List<Doctor> getAllDoctors() {
        return dao.getAllDoctors();
    }

    @Override
    @Transactional
    public void saveDoctor(Doctor doctor) {
        dao.saveDoctor(doctor);
    }

    @Override
    @Transactional
    public Doctor getDoctor(int id) {
        return dao.getDoctor(id);
    }

    @Override
    @Transactional
    public Doctor getDoctor(String username) {
        return dao.getDoctor(username);
    }

    @Override
    @Transactional
    public void deleteDoctor(int id) {
        dao.deleteDoctor(id);
    }

    @Override
    @Transactional
    public List<Patient> getAllPatients() {
        return dao.getAllPatients();
    }

    @Override
    @Transactional
    public void savePatient(Patient patient) {
        dao.savePatient(patient);
    }

    @Override
    @Transactional
    public Patient getPatient(int id) {
        return dao.getPatient(id);
    }

    @Override
    @Transactional
    public Patient getPatient(String username) {
        return dao.getPatient(username);
    }

    @Override
    @Transactional
    public void deletePatient(int id) {
        dao.deletePatient(id);
    }
}
