package com.ns.backend.Service;

import com.ns.backend.Entity.Doctor;

import java.util.List;

public interface DoctorService {

    List<Doctor> findAll();
    void save(Doctor doctor);
    void deleteById(int id);
    Doctor findById(int id);

}
