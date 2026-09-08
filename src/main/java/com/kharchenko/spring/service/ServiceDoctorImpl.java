package com.kharchenko.spring.service;

import com.kharchenko.spring.entity.AgeNorm;
import com.kharchenko.spring.entity.Audiogram;
import com.kharchenko.spring.entity.Median;
import com.kharchenko.spring.entity.Patient;
import com.kharchenko.spring.repository.DAODoctor;
import com.kharchenko.spring.repository.DAOMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ServiceDoctorImpl implements ServiceDoctor{
    @Autowired
    private DAODoctor dao;

    @Override
    @Transactional
    public List<Patient> getDoctorsPatients(String username) {
        return dao.getDoctorsPatients(username);
    }

    @Override
    @Transactional
    public List<Audiogram> getAudiogramPatient(String username) {
        return dao.getAudiogramPatient(username);
    }

    @Override
    @Transactional
    public Median getMedianPatient(String username) {
        return dao.getMedianPatient(username);
    }

    @Override
    @Transactional
    public AgeNorm getAgeNormPatient(int age, int sex) {
        return dao.getAgeNormPatient(age, sex);
    }
}
