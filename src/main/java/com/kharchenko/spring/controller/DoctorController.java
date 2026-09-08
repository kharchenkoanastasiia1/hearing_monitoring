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
@RequestMapping("/doctor")
public class DoctorController {
    @Autowired
    private Service service;
    @Autowired
    private ServiceMessage serviceMessage;
    @Autowired
    private EmailServiceImpl emailService;

    private String senderUsernameDoctor;

    @GetMapping("/allPatient")
    public String showAllInformation() {
        return "doctor-panel";
    }

    @GetMapping("/addMessage")
    public String addNewMessageDoctor(@RequestParam("usernamePatient") String usernamePatient, @RequestParam("doctorUsername") String usernameDoctor, Model model){
        Message message = new Message();
        message.setUsername(usernamePatient);
        senderUsernameDoctor = usernameDoctor;
        model.addAttribute("message", message);
        return "new-message-doctor";
    }

    @PostMapping("/saveMessage")
    public String saveMessage(@ModelAttribute("message") Message message){
        Patient patient = service.getPatient(message.getUsername());
        Doctor doctor = service.getDoctor(senderUsernameDoctor);
        emailService.sendSimpleMessage(patient.getEmail(), "Повідомлення від Hearing Monitoring", "Повідомлення від вашого лікаря " + doctor.getName() + " " + doctor.getSurname() + ": \n" + message.getText());
        message.setDate(new Date());
        message.setStatus(1);
        message.setText("Повідомлення від вашого лікаря " + doctor.getName() + " " + doctor.getSurname() + ": " + message.getText());
        serviceMessage.saveMessage(message);
        return "redirect:/doctor/allPatient";
    }
}
