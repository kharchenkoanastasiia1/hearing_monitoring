package com.kharchenko.spring.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="patient")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="username")
    private String username;
    @Column(name="name")
    private String name;
    @Column(name="surname")
    private String surname;
    @Column(name="age")
    private int age;
    @Column(name="sex")
    private int sex;
    @Column(name="email")
    private String email;

//    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH}, targetEntity = Doctor.class)
//    @JoinColumn(name="username", nullable=false)
    @Column(name="doctor_username")
    private String doctorUsername;

//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name="username")
    @Transient
    private List<Audiogram> audiograms;

//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name="username")
    @Transient
    private List<Median> medians;

//    @OneToOne(mappedBy = "patient", cascade = CascadeType.ALL)
    @Transient
    private User user;

    public Patient() {}

    public Patient(String username, String name, String surname, int age, int sex, String email, String doctorUsername) {
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.sex = sex;
        this.email = email;
        this.doctorUsername = doctorUsername;
    }

//    public Patient(String username, String name, String surname, int age, int sex, String email, String doctorUsername, List<Audiogram> audiograms, List<Median> medians, User user) {
//        this.username = username;
//        this.name = name;
//        this.surname = surname;
//        this.age = age;
//        this.sex = sex;
//        this.email = email;
//        this.doctorUsername = doctorUsername;
//        this.audiograms = audiograms;
//        this.medians = medians;
//        this.user = user;
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Audiogram> getAudiograms() {
        return audiograms;
    }

    public void setAudiograms(List<Audiogram> audiograms) {
        this.audiograms = audiograms;
    }

    public List<Median> getMedians() {
        return medians;
    }

    public void setMedians(List<Median> medians) {
        this.medians = medians;
    }

    public String getDoctorUsername() {
        return doctorUsername;
    }

    public void setDoctorUsername(String doctorUsername) {
        this.doctorUsername = doctorUsername;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                ", email='" + email + '\'' +
                ", doctorUsername='" + doctorUsername + '\'' +
                '}';
    }
}
