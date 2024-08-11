package com.ns.backend.Service;

import com.ns.backend.Entity.Patient;

import java.util.List;

public interface PatientService {


    List<Patient> findAll();
    void save(Patient patient);
    void deleteById(int id);
    Patient findById(int id);
}
