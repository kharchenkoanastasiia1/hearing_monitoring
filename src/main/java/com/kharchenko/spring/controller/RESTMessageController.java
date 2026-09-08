package com.kharchenko.spring.controller;

import com.kharchenko.spring.entity.Doctor;
import com.kharchenko.spring.entity.Message;
import com.kharchenko.spring.entity.Patient;
import com.kharchenko.spring.service.Service;
import com.kharchenko.spring.service.ServiceMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/message")
public class RESTMessageController {
    @Autowired
    private ServiceMessage serviceMessage;
    @Autowired
    private Service service;

    @GetMapping("/showPatient")
    public List<Message> showMessagesPatient(@RequestParam("patientId") int id) {
        Patient patient = service.getPatient(id);
        return serviceMessage.getMessagesByUsername(patient.getUsername());
    }

    @GetMapping("/showDoctor")
    public List<Message> showMessagesDoctor(@RequestParam("doctorId") int id) {
        Doctor doctor = service.getDoctor(id);
        return serviceMessage.getMessagesByUsername(doctor.getUsername());
    }

    @GetMapping("/showDoctorMessagesByUsername")
    public List<Message> showDoctorMessagesByUsername(@RequestParam("usernameDoctor") String username) {
        List<Message> allMessages = serviceMessage.getMessagesByUsername(username);
        return serviceMessage.getMessagesByUsername(username);
    }

    @GetMapping("/showPatientMessagesByUsername")
    public List<Message> showPatientMessagesByUsername(@RequestParam("usernamePatient") String username) {
        List<Message> allMessages = serviceMessage.getMessagesByUsername(username);
        return serviceMessage.getMessagesByUsername(username);
    }
}
