package com.kharchenko.spring.proprietary;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kharchenko.spring.entity.AgeNorm;
import com.kharchenko.spring.entity.Audiogram;
import com.kharchenko.spring.entity.Median;

import java.util.Date;

public class AudiogramForGraphic {
    private String username;
    private Integer[] left;
    private Integer[] right;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date date;

    private String resultAnalizeLeft;
    private String resultAnalizeRight;

    public AudiogramForGraphic(Audiogram audiogram) {
        this.username = audiogram.getUsername();
        this.date = audiogram.getDate();
        this.left = new Integer[]{audiogram.getF125Left(), audiogram.getF250Left(), audiogram.getF500Left(), audiogram.getF1000Left(), audiogram.getF2000Left(), audiogram.getF3000Left(), audiogram.getF4000Left(), audiogram.getF8000Left()};
        this.right = new Integer[]{audiogram.getF125Right(), audiogram.getF250Right(), audiogram.getF500Right(), audiogram.getF1000Right(), audiogram.getF2000Right(), audiogram.getF3000Right(), audiogram.getF4000Right(), audiogram.getF8000Right()};
    }

    public AudiogramForGraphic(Median median) {
        this.username = median.getUsername();
        this.date = median.getDate();
        this.left = new Integer[]{median.getF125Left(), median.getF250Left(), median.getF500Left(), median.getF1000Left(), median.getF2000Left(), median.getF3000Left(), median.getF4000Left(), median.getF8000Left()};
        this.right = new Integer[]{median.getF125Right(), median.getF250Right(), median.getF500Right(), median.getF1000Right(), median.getF2000Right(), median.getF3000Right(), median.getF4000Right(), median.getF8000Right()};
    }

    public AudiogramForGraphic(AgeNorm ageNorm) {
        this.username = "";
        this.date = new Date();
        this.left = new Integer[]{ageNorm.getF125(), ageNorm.getF250(), ageNorm.getF500(), ageNorm.getF1000(), ageNorm.getF2000(), ageNorm.getF3000(), ageNorm.getF4000(), ageNorm.getF8000()};
        this.right = new Integer[]{ageNorm.getF125(), ageNorm.getF250(), ageNorm.getF500(), ageNorm.getF1000(), ageNorm.getF2000(), ageNorm.getF3000(), ageNorm.getF4000(), ageNorm.getF8000()};
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer[] getLeft() {
        return left;
    }

    public void setLeft(Integer[] left) {
        this.left = left;
    }

    public Integer[] getRight() {
        return right;
    }

    public void setRight(Integer[] right) {
        this.right = right;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getResultAnalizeLeft() {
        return resultAnalizeLeft;
    }

    public void setResultAnalizeLeft(String resultAnalizeLeft) {
        this.resultAnalizeLeft = resultAnalizeLeft;
    }

    public String getResultAnalizeRight() {
        return resultAnalizeRight;
    }

    public void setResultAnalizeRight(String resultAnalizeRight) {
        this.resultAnalizeRight = resultAnalizeRight;
    }
}
