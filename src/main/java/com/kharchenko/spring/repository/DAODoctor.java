package com.kharchenko.spring.repository;

import com.kharchenko.spring.entity.*;

import java.util.List;

public interface DAODoctor {

    public List<Patient> getDoctorsPatients(String username);

    public List<Audiogram> getAudiogramPatient(String username);

    public Median getMedianPatient(String username);

    public AgeNorm getAgeNormPatient(int age, int sex);
}
