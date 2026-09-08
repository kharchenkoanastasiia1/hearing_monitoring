package com.kharchenko.spring.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kharchenko.spring.entity.*;
import com.kharchenko.spring.service.Service;
import com.kharchenko.spring.service.ServicePatient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.text.ParseException;
import java.util.*;

@RestController
@RequestMapping("/patient")
public class RESTPatientController {

    @Autowired
    private Service service;
    @Autowired
    private ServicePatient servicePatient;

    @GetMapping("/patients")
    public List<Patient> showAllPatients() {
        return service.getAllPatients();
    }

    @PostMapping( "/audiogram")
    public void showAllAudiograms(@RequestBody String audiogram) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Audiogram> audList = objectMapper.readValue(audiogram, new TypeReference<List<Audiogram>>(){});

        for(Audiogram aud : audList){
            servicePatient.saveAudiogram(aud);
        }
    }

    @PostMapping( "/median")
    public void showMedian(@RequestBody String median) throws JsonProcessingException, ParseException {
        ObjectMapper objectMapper = new ObjectMapper();
        Median med = objectMapper.readValue(median, Median.class);
        servicePatient.saveMedian(med);
    }

    @GetMapping("info/lastDate")
    public String showLastDate(@RequestParam("usernamePatient") String username) throws ParseException {
        Date lastDateAudiogram = servicePatient.getAudiogramDate(username);
        Date lastDateMedian = servicePatient.getMedianDate(username);
        StringBuilder str = new StringBuilder();
        str.append("{\"lastDateAudiogram\":");
        if(lastDateAudiogram == null){
            str.append("\"1970-01-01\"");
        } else{
            str.append("\"").append(lastDateAudiogram).append("\"");
        }
        str.append(",\"lastDateMedian\":");
        if(lastDateMedian == null){
            str.append("\"1970-01-01\"");
        } else{
            str.append("\"").append(lastDateMedian).append("\"");
        }
        str.append("}");
        return str.toString();
    }
}
