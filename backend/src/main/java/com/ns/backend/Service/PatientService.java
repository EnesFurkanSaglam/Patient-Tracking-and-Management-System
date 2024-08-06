package com.ns.backend.Service;

import com.ns.backend.Entity.Patient;

import java.util.List;

public interface PatientService {

    List<Patient> findAll();
    Patient findById(int theId);
    void save(Patient thePatient);
    void deleteById(int theId);
}
