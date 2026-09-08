package com.kharchenko.spring.entity;

import javax.persistence.*;

@Entity
@Table(name="age_norm")
public class AgeNorm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="age_min")
    private int ageMin;
    @Column(name="age_max")
    private int ageMax;
    @Column(name="sex")
    private int sex;
    @Column(name="f125")
    private int f125;
    @Column(name="f250")
    private int f250;
    @Column(name="f500")
    private int f500;
    @Column(name="f1000")
    private int f1000;
    @Column(name="f2000")
    private int f2000;
    @Column(name="f3000")
    private int f3000;
    @Column(name="f4000")
    private int f4000;
    @Column(name="f8000")
    private int f8000;

    public AgeNorm() {}

    public AgeNorm(int ageMin, int ageMax, int sex, int f125, int f250, int f500, int f1000, int f2000, int f3000, int f4000, int f8000) {
        this.ageMin = ageMin;
        this.ageMax = ageMax;
        this.sex = sex;
        this.f125 = f125;
        this.f250 = f250;
        this.f500 = f500;
        this.f1000 = f1000;
        this.f2000 = f2000;
        this.f3000 = f3000;
        this.f4000 = f4000;
        this.f8000 = f8000;
    }

    public int getAgeMin() {
        return ageMin;
    }

    public void setAgeMin(int ageMin) {
        this.ageMin = ageMin;
    }

    public int getAgeMax() {
        return ageMax;
    }

    public void setAgeMax(int ageMax) {
        this.ageMax = ageMax;
    }

    public int getF125() {
        return f125;
    }

    public void setF125(int f125) {
        this.f125 = f125;
    }

    public int getF250() {
        return f250;
    }

    public void setF250(int f250) {
        this.f250 = f250;
    }

    public int getF500() {
        return f500;
    }

    public void setF500(int f500) {
        this.f500 = f500;
    }

    public int getF1000() {
        return f1000;
    }

    public void setF1000(int f1000) {
        this.f1000 = f1000;
    }

    public int getF2000() {
        return f2000;
    }

    public void setF2000(int f2000) {
        this.f2000 = f2000;
    }

    public int getF3000() {
        return f3000;
    }

    public void setF3000(int f3000) {
        this.f3000 = f3000;
    }

    public int getF4000() {
        return f4000;
    }

    public void setF4000(int f4000) {
        this.f4000 = f4000;
    }

    public int getF8000() {
        return f8000;
    }

    public void setF8000(int f8000) {
        this.f8000 = f8000;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }
}
