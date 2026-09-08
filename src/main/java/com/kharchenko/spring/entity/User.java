package com.kharchenko.spring.entity;

import javax.persistence.*;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="username", nullable = false)
    private String username;
    @Column(name="password")
    private String password;
    @Column(name="enabled")
    private int enabled;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name="username", insertable=false, updatable=false)
    @Transient
    private Authorities authorityUser;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name="username", insertable=false, updatable=false)
    @Transient
    private Patient patient;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name="username", insertable=false, updatable=false)
    @Transient
    private Doctor doctor;

    public User() {}

    public User(int id, String username, String password, int enabled) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.enabled = enabled;
    }

    public User(String username, String password, int enabled) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public Authorities getAuthorityUser() {
        return authorityUser;
    }

    public void setAuthorityUser(Authorities authorityUser) {
        this.authorityUser = authorityUser;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
}
