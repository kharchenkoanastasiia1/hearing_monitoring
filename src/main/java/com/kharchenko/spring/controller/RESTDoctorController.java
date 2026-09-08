package com.kharchenko.spring.controller;

import com.kharchenko.spring.proprietary.AudiogramForGraphic;
import com.kharchenko.spring.proprietary.WorkWithCollectionData;
import com.kharchenko.spring.entity.*;
import com.kharchenko.spring.service.Service;
import com.kharchenko.spring.service.ServiceDoctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/doctor")
public class RESTDoctorController {
    @Autowired
    private Service service;
    @Autowired
    private ServiceDoctor serviceDoctor;

    private List<AudiogramForGraphic> allAudiogramForGraphic;

    @GetMapping("/doctors")
    public List<Doctor> showAllDoctors() {
        return service.getAllDoctors();
    }

    @GetMapping("/allDoctorsPatients")
    public List<Patient> showAllDoctrosPatients(@RequestParam("usernameDoctor") String username){
        return serviceDoctor.getDoctorsPatients(username);
    }

    @GetMapping("/allAudiogramsPatient")
    public List<AudiogramForGraphic> showAllAudiogramsPatient(@RequestParam("usernamePatient") String username){
        List<Audiogram> allAudiograms = serviceDoctor.getAudiogramPatient(username);
        allAudiogramForGraphic = WorkWithCollectionData.getListAudiogramForGraphic(allAudiograms);
        return allAudiogramForGraphic;
    }

    @GetMapping("/allAudiogramsPatientFrequency")
    public List<Audiogram> showAllAudiogramsPatientFrequency(@RequestParam("usernamePatient") String username){
        return serviceDoctor.getAudiogramPatient(username);
    }

    @GetMapping("/median")
    public AudiogramForGraphic showMedian(@RequestParam("usernamePatient") String username, @RequestParam("idAudiogram") int index){
        Median median = serviceDoctor.getMedianPatient(username);
        AudiogramForGraphic audiogramForGraphic = new AudiogramForGraphic(median);
        audiogramForGraphic.setResultAnalizeLeft(WorkWithCollectionData.normAnalize(allAudiogramForGraphic.get(index), audiogramForGraphic, 0, true));
        audiogramForGraphic.setResultAnalizeRight(WorkWithCollectionData.normAnalize(allAudiogramForGraphic.get(index), audiogramForGraphic, 1, true));
        return audiogramForGraphic;
    }

    @GetMapping("/ageNorm")
    public AudiogramForGraphic showAgeNorm(@RequestParam("patientId") int id, @RequestParam("idAudiogram") int index){
        Patient patient = service.getPatient(id);
        AgeNorm ageNorm = serviceDoctor.getAgeNormPatient(patient.getAge(), patient.getSex());
        AudiogramForGraphic audiogramForGraphic = new AudiogramForGraphic(ageNorm);
        audiogramForGraphic.setResultAnalizeLeft(WorkWithCollectionData.normAnalize(allAudiogramForGraphic.get(index), audiogramForGraphic, 0, false));
        audiogramForGraphic.setResultAnalizeRight(WorkWithCollectionData.normAnalize(allAudiogramForGraphic.get(index), audiogramForGraphic, 1, false));
        return audiogramForGraphic;
    }
}