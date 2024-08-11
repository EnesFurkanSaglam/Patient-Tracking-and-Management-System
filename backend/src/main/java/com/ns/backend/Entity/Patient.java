package com.ns.backend.Entity;
import jakarta.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "patients")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer patientId;

    @Column(name = "patient_name", nullable = false)
    private String patientName;

    @Column(name = "patient_surname", nullable = false)
    private String patientSurname;

    @Column(name = "birthday")
    private Date birthday;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @Column(name = "phone")
    private String phone;

    @Column(name = "address")
    private String address;


    public Patient() {
    }

    public Patient(String patientName, String patientSurname, Date birthday, Gender gender, String phone, String address) {
        this.patientName = patientName;
        this.patientSurname = patientSurname;
        this.birthday = birthday;
        this.gender = gender;
        this.phone = phone;
        this.address = address;
    }

    public Patient(Integer patientId, String patientName, String patientSurname, Date birthday, Gender gender, String phone, String address) {
        this.patientId = patientId;
        this.patientName = patientName;
        this.patientSurname = patientSurname;
        this.birthday = birthday;
        this.gender = gender;
        this.phone = phone;
        this.address = address;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientSurname() {
        return patientSurname;
    }

    public void setPatientSurname(String patientSurname) {
        this.patientSurname = patientSurname;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "patientId=" + patientId +
                ", patientName='" + patientName + '\'' +
                ", patientSurname='" + patientSurname + '\'' +
                ", birthday=" + birthday +
                ", gender=" + gender +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public enum Gender {
        Male, Female
    }
}




