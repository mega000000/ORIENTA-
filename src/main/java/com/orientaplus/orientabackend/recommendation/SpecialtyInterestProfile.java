package com.orientaplus.orientabackend.recommendation;

import com.orientaplus.orientabackend.specialty.Specialty;
import jakarta.persistence.*;

@Entity
public class SpecialtyInterestProfile {

    @Id
    @GeneratedValue
    private long id;

    @OneToOne
    @JoinColumn(name = "specialty_id", unique = true)
    private Specialty specialty;

    private double r;
    private double i;
    private double a;
    private double s;
    private double e;
    private double c;

    public SpecialtyInterestProfile(){}

    public SpecialtyInterestProfile(Specialty specialty, double r, double i, double a, double s, double e, double c){
        this.specialty = specialty;
        this.r = r;
        this.i = i;
        this.a = a;
        this.s = s;
        this.e = e;
        this.c = c;
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public Specialty getSpecialty() { return specialty; }
    public void setSpecialty(Specialty specialty) { this.specialty = specialty; }

    public double getR() { return r; }
    public void setR(double r) { this.r = r; }

    public double getI() { return i; }
    public void setI(double i) { this.i = i; }

    public double getA() { return a; }
    public void setA(double a) { this.a = a; }

    public double getS() { return s; }
    public void setS(double s) { this.s = s; }

    public double getE() { return e; }
    public void setE(double e) { this.e = e; }

    public double getC() { return c; }
    public void setC(double c) { this.c = c; }
}