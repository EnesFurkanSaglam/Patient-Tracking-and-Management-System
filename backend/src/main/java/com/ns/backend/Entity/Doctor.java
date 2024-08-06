package com.ns.backend.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "doctors")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer doctorId;

    @Column(name = "doctor_name", nullable = false)
    private String doctorName;

    @Column(name = "doctor_surname", nullable = false)
    private String doctorSurname;

    @Column(name = "expertise")
    private String expertise;

    @Column(name = "hospital")
    private String hospital;

    public Doctor() {
    }

    public Doctor(String doctorName, String doctorSurname, String expertise, String hospital) {
        this.doctorName = doctorName;
        this.doctorSurname = doctorSurname;
        this.expertise = expertise;
        this.hospital = hospital;
    }

    public Doctor(Integer doctorId, String doctorName, String doctorSurname, String expertise, String hospital) {
        this.doctorId = doctorId;
        this.doctorName = doctorName;
        this.doctorSurname = doctorSurname;
        this.expertise = expertise;
        this.hospital = hospital;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getExpertise() {
        return expertise;
    }

    public void setExpertise(String expertise) {
        this.expertise = expertise;
    }

    public String getDoctorSurname() {
        return doctorSurname;
    }

    public void setDoctorSurname(String doctorSurname) {
        this.doctorSurname = doctorSurname;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "doctorId=" + doctorId +
                ", doctorName='" + doctorName + '\'' +
                ", doctorSurname='" + doctorSurname + '\'' +
                ", expertise='" + expertise + '\'' +
                ", hospital='" + hospital + '\'' +
                '}';
    }
}
