package com.kharchenko.spring.repository;

import com.kharchenko.spring.entity.Audiogram;
import com.kharchenko.spring.entity.Median;

import java.text.ParseException;
import java.util.Date;

public interface DAOPatient {

    public void saveAudiogram(Audiogram audiogram);

    public void saveMedian(Median median);

    public Date getAudiogramDate(String username) throws ParseException;

    public Date getMedianDate(String username) throws ParseException;

}
