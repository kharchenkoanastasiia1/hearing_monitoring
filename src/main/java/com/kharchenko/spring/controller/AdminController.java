package com.kharchenko.spring.controller;

import com.kharchenko.spring.proprietary.GeneratePassword;
import com.kharchenko.spring.entity.*;
import com.kharchenko.spring.service.EmailServiceImpl;
import com.kharchenko.spring.service.Service;
import com.kharchenko.spring.service.ServiceMessage;
import com.kharchenko.spring.service.ServiceUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Date;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private Service service;
    @Autowired
    private ServiceUser serviceUser;
    @Autowired
    private ServiceMessage serviceMessage;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private EmailServiceImpl emailService;
    private Boolean statusDoctor;
    private Boolean statusPatient;

    @GetMapping("/allInfo")
    public String showAllInformation() {
        return "admin-panel";
    }

    @GetMapping("/addDoctor")
    public String addNewDoctor(Model model){
        statusDoctor = true;
        Doctor doctor = new Doctor();
        model.addAttribute("doctor", doctor);
        return "doctor-info";
    }

    @PostMapping("/saveDoctor")
    public String saveDoctor(@ModelAttribute("doctor") Doctor doctor){
        if(statusDoctor){
            String password = GeneratePassword.generateRandomPassword(10);
            serviceUser.saveUser(new User(doctor.getUsername(), passwordEncoder.encode(password), 1));
            serviceUser.saveAuthorities(new Authorities(doctor.getUsername(), "ROLE_DOCTOR"));
            emailService.sendSimpleMessage(doctor.getEmail(), "Повідомлення від Hearing Monitoring", "Ваш логін: " + doctor.getUsername() + "\nСистема згенерувала ваш пароль: " + password);
            serviceMessage.saveMessage(new Message(doctor.getUsername(), "Система згенерувала ваш пароль: " + password, new Date(), 1));
        }
        service.saveDoctor(doctor);
        return "redirect:/admin/allInfo";
    }

    @GetMapping("/updateDoctor")
    public String updateDoctor(@RequestParam("doctorId") int id, Model model){
        statusDoctor = false;
        Doctor doctor = service.getDoctor(id);
        model.addAttribute("doctor", doctor);
        return "doctor-info";
    }

    @GetMapping("/deleteDoctor")
    public String deleteDoctor(@RequestParam("doctorId") int id){
        Doctor doctor = service.getDoctor(id);
        serviceUser.deleteUser(doctor.getUsername());
        serviceUser.deleteAuthorities(doctor.getUsername());
        service.deleteDoctor(id);
        return "redirect:/admin/allInfo";
    }

    @GetMapping("/addPatient")
    public String addNewPatient(Model model){
        statusPatient = true;
        Patient patient = new Patient();
        model.addAttribute("patient", patient);
        return "patient-info";
    }

    @PostMapping("/savePatient")
    public String savePatient(@ModelAttribute("patient") Patient patient){
        if(statusPatient){
            String password = GeneratePassword.generateRandomPassword(15);
            serviceUser.saveUser(new User(patient.getUsername(), passwordEncoder.encode(password), 1));
            serviceUser.saveAuthorities(new Authorities(patient.getUsername(), "ROLE_PATIENT"));
            emailService.sendSimpleMessage(patient.getEmail(), "Повідомлення від Hearing Monitoring", "Ваш логін: " + patient.getUsername() + "\nСистема згенерувала ваш пароль: " + password);
            serviceMessage.saveMessage(new Message(patient.getUsername(), "Система згенерувала ваш пароль: " + password, new Date(), 1));
        }
        service.savePatient(patient);
        return "redirect:/admin/allInfo";
    }

    @GetMapping("/updatePatient")
    public String updatePatient(@RequestParam("patientId") int id, Model model){
        statusPatient = false;
        Patient patient = service.getPatient(id);
        model.addAttribute("patient", patient);
        return "patient-info";
    }

    @GetMapping("/deletePatient")
    public String deletePatient(@RequestParam("patientId") int id){
        Patient patient = service.getPatient(id);
        serviceUser.deleteUser(patient.getUsername());
        serviceUser.deleteAuthorities(patient.getUsername());
        service.deletePatient(id);
        return "redirect:/admin/allInfo";
    }

    @GetMapping("/generatePasswordPatient")
    public String generatePasswordPatient(@RequestParam("patientId") int id){
        Patient patient = service.getPatient(id);
        User user = serviceUser.getUserByUsername(patient.getUsername());
        String password = GeneratePassword.generateRandomPassword(10);
        user.setPassword(passwordEncoder.encode(password));
        serviceUser.saveUser(user);
        emailService.sendSimpleMessage(patient.getEmail(), "Повідомлення від Hearing Monitoring", "Ваш логін: " + patient.getUsername() + "\nСистема перегенерувала ваш пароль: " + password);
        serviceMessage.saveMessage(new Message(patient.getUsername(), "Система перегенерувала ваш пароль: " + password, new Date(), 1));
        return "redirect:/admin/allInfo";
    }

    @GetMapping("/generatePasswordDoctor")
    public String generatePasswordDoctor(@RequestParam("doctorId") int id){
        Doctor doctor = service.getDoctor(id);
        User user = serviceUser.getUserByUsername(doctor.getUsername());
        String password = GeneratePassword.generateRandomPassword(10);
        user.setPassword(passwordEncoder.encode(password));
        serviceUser.saveUser(user);
        emailService.sendSimpleMessage(doctor.getEmail(), "Повідомлення від Hearing Monitoring", "Ваш логін: " + doctor.getUsername() + "\nСистема перегенерувала ваш пароль: " + password);
        serviceMessage.saveMessage(new Message(doctor.getUsername(), "Система перегенерувала ваш пароль: " + password, new Date(), 1));
        return "redirect:/admin/allInfo";
    }
}
