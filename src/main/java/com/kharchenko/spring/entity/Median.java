package com.kharchenko.spring.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="median")
public class Median {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="username")
    private String username;
    @Column(name="f125_left")
    private int f125Left;
    @Column(name="f250_left")
    private int f250Left;
    @Column(name="f500_left")
    private int f500Left;
    @Column(name="f1000_left")
    private int f1000Left;
    @Column(name="f2000_left")
    private int f2000Left;
    @Column(name="f3000_left")
    private int f3000Left;
    @Column(name="f4000_left")
    private int f4000Left;
    @Column(name="f8000_left")
    private int f8000Left;
    @Column(name="f125_right")
    private int f125Right;
    @Column(name="f250_right")
    private int f250Right;
    @Column(name="f500_right")
    private int f500Right;
    @Column(name="f1000_right")
    private int f1000Right;
    @Column(name="f2000_right")
    private int f2000Right;
    @Column(name="f3000_right")
    private int f3000Right;
    @Column(name="f4000_right")
    private int f4000Right;
    @Column(name="f8000_right")
    private int f8000Right;
    @Column(name="date")
    private Date date;

    public Median() {}

    public Median(String username, int f125Left, int f250Left, int f500Left, int f1000Left, int f2000Left, int f3000Left, int f4000Left, int f8000Left, int f125Right, int f250Right, int f500Right, int f1000Right, int f2000Right, int f3000Right, int f4000Right, int f8000Right, Date date) {
        this.username = username;
        this.f125Left = f125Left;
        this.f250Left = f250Left;
        this.f500Left = f500Left;
        this.f1000Left = f1000Left;
        this.f2000Left = f2000Left;
        this.f3000Left = f3000Left;
        this.f4000Left = f4000Left;
        this.f8000Left = f8000Left;
        this.f125Right = f125Right;
        this.f250Right = f250Right;
        this.f500Right = f500Right;
        this.f1000Right = f1000Right;
        this.f2000Right = f2000Right;
        this.f3000Right = f3000Right;
        this.f4000Right = f4000Right;
        this.f8000Right = f8000Right;
        this.date = date;
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

    public int getF125Left() {
        return f125Left;
    }

    public void setF125Left(int f125Left) {
        this.f125Left = f125Left;
    }

    public int getF250Left() {
        return f250Left;
    }

    public void setF250Left(int f250Left) {
        this.f250Left = f250Left;
    }

    public int getF500Left() {
        return f500Left;
    }

    public void setF500Left(int f500Left) {
        this.f500Left = f500Left;
    }

    public int getF1000Left() {
        return f1000Left;
    }

    public void setF1000Left(int f1000Left) {
        this.f1000Left = f1000Left;
    }

    public int getF2000Left() {
        return f2000Left;
    }

    public void setF2000Left(int f2000Left) {
        this.f2000Left = f2000Left;
    }

    public int getF3000Left() {
        return f3000Left;
    }

    public void setF3000Left(int f3000Left) {
        this.f3000Left = f3000Left;
    }

    public int getF4000Left() {
        return f4000Left;
    }

    public void setF4000Left(int f4000Left) {
        this.f4000Left = f4000Left;
    }

    public int getF8000Left() {
        return f8000Left;
    }

    public void setF8000Left(int f8000Left) {
        this.f8000Left = f8000Left;
    }

    public int getF125Right() {
        return f125Right;
    }

    public void setF125Right(int f125Right) {
        this.f125Right = f125Right;
    }

    public int getF250Right() {
        return f250Right;
    }

    public void setF250Right(int f250Right) {
        this.f250Right = f250Right;
    }

    public int getF500Right() {
        return f500Right;
    }

    public void setF500Right(int f500Right) {
        this.f500Right = f500Right;
    }

    public int getF1000Right() {
        return f1000Right;
    }

    public void setF1000Right(int f1000Right) {
        this.f1000Right = f1000Right;
    }

    public int getF2000Right() {
        return f2000Right;
    }

    public void setF2000Right(int f2000Right) {
        this.f2000Right = f2000Right;
    }

    public int getF3000Right() {
        return f3000Right;
    }

    public void setF3000Right(int f3000Right) {
        this.f3000Right = f3000Right;
    }

    public int getF4000Right() {
        return f4000Right;
    }

    public void setF4000Right(int f4000Right) {
        this.f4000Right = f4000Right;
    }

    public int getF8000Right() {
        return f8000Right;
    }

    public void setF8000Right(int f8000Right) {
        this.f8000Right = f8000Right;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Audiogram{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", f125Left=" + f125Left +
                ", f250Left=" + f250Left +
                ", f500Left=" + f500Left +
                ", f1000Left=" + f1000Left +
                ", f2000Left=" + f2000Left +
                ", f3000Left=" + f3000Left +
                ", f4000Left=" + f4000Left +
                ", f8000Left=" + f8000Left +
                ", f125Right=" + f125Right +
                ", f250Right=" + f250Right +
                ", f500Right=" + f500Right +
                ", f1000Right=" + f1000Right +
                ", f2000Right=" + f2000Right +
                ", f3000Right=" + f3000Right +
                ", f4000Right=" + f4000Right +
                ", f8000Right=" + f8000Right +
                ", date=" + date +
                '}';
    }
}
