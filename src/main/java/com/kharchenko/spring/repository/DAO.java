package com.kharchenko.spring.repository;

import com.kharchenko.spring.entity.Doctor;
import com.kharchenko.spring.entity.Patient;

import java.util.List;

public interface DAO {
    public List<Doctor> getAllDoctors();

    public void saveDoctor(Doctor doctor);

    public Doctor getDoctor(int id);

    public Doctor getDoctor(String username);

    public void deleteDoctor(int id);

    public List<Patient> getAllPatients();

    public void savePatient(Patient patient);

    public Patient getPatient(int id);

    public Patient getPatient(String username);

    public void deletePatient(int id);
}
