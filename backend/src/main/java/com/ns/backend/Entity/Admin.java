package com.ns.backend.Entity;


import jakarta.persistence.*;


@Entity
@Table(name = "admin")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer adminId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    public Admin() {
    }

    public Admin(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public Admin(Integer adminId, String name, String surname) {
        this.adminId = adminId;
        this.name = name;
        this.surname = surname;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
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

    @Override
    public String toString() {
        return "Admin{" +
                "adminId=" + adminId +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}




