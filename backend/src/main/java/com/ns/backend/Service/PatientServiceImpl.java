package com.ns.backend.Service;


import com.ns.backend.Dao.PatientRepository;
import com.ns.backend.Entity.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService {


    private PatientRepository patientRepository;


    @Autowired
    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    @Override
    public void save(Patient patient) {
        patientRepository.save(patient);
    }

    @Override
    public void deleteById(int id) {
        patientRepository.deleteById(id);
    }

    @Override
    public Patient findById(int id) {

        Optional<Patient> result = patientRepository.findById(id);

        Patient patient = null;

        if (result.isPresent()){
            patient = result.get();

        }else{
            throw new RuntimeException("Did not find patient id - " + id);
        }

        return patient;
    }

}
