package com.kharchenko.spring.controller;

import com.kharchenko.spring.entity.Doctor;
import com.kharchenko.spring.entity.Message;
import com.kharchenko.spring.entity.Patient;
import com.kharchenko.spring.service.EmailServiceImpl;
import com.kharchenko.spring.service.Service;
import com.kharchenko.spring.service.ServiceMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
@RequestMapping("/patient")
public class PatientController {
    @Autowired
    private Service service;
    @Autowired
    private ServiceMessage serviceMessage;
    @Autowired
    private EmailServiceImpl emailService;

    private String senderUsernamePatient;

    @GetMapping("/info")
    public String showAllInformationByPatient(){
        return "patient-panel";
    }

    @GetMapping("/addMessagePatient")
    public String addNewMessagePatient(@RequestParam("usernamePatient") String usernamePatient, Model model){
        Message message = new Message();
        senderUsernamePatient = usernamePatient;
        model.addAttribute("message", message);
        return "new-message-patient";
    }

    @PostMapping("/saveMessagePatient")
    public String saveMessagePatient(@ModelAttribute("message") Message message){
        Patient patient = service.getPatient(senderUsernamePatient);
        Doctor doctor = service.getDoctor(patient.getDoctorUsername());
        emailService.sendSimpleMessage(doctor.getEmail(), "Повідомлення від Hearing Monitoring", "Повідомлення від вашого пацієнта " + patient.getName() + " " + patient.getSurname() + ": \n" + message.getText());
        message.setUsername(patient.getDoctorUsername());
        message.setDate(new Date());
        message.setStatus(1);
        message.setText("Повідомлення від вашого пацієнта " + patient.getName() + " " + patient.getSurname() + ": " + message.getText());
        serviceMessage.saveMessage(message);
        return "redirect:/patient/info";
    }
}
