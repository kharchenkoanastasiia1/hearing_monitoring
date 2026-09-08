package com.kharchenko.spring.service;

import com.kharchenko.spring.entity.Audiogram;
import com.kharchenko.spring.entity.Median;
import com.kharchenko.spring.repository.DAOPatient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.Date;

@Service
@Transactional
public class ServicePatientImpl implements ServicePatient{
    @Autowired
    private DAOPatient dao;

    @Override
    @Transactional
    public void saveAudiogram(Audiogram audiogram) {
        dao.saveAudiogram(audiogram);
    }

    @Override
    public void saveMedian(Median median) {
        dao.saveMedian(median);
    }

    @Override
    @Transactional
    public Date getAudiogramDate(String username) throws ParseException {
        return dao.getAudiogramDate(username);
    }

    @Override
    @Transactional
    public Date getMedianDate(String username) throws ParseException {
        return dao.getMedianDate(username);
    }
}
