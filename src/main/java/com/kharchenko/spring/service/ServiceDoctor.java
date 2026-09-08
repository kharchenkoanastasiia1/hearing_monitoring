package com.kharchenko.spring.service;

import com.kharchenko.spring.entity.AgeNorm;
import com.kharchenko.spring.entity.Audiogram;
import com.kharchenko.spring.entity.Median;
import com.kharchenko.spring.entity.Patient;

import java.util.List;

public interface ServiceDoctor {

    public List<Patient> getDoctorsPatients(String username);

    public List<Audiogram> getAudiogramPatient(String username);

    public Median getMedianPatient(String username);

    public AgeNorm getAgeNormPatient(int age, int sex);
}
