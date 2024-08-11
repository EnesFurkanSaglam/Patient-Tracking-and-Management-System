package com.ns.backend.Service;

import com.ns.backend.Dao.DoctorRepository;
import com.ns.backend.Entity.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;


@Service
public class DoctorServiceImpl implements DoctorService {


    private DoctorRepository doctorRepository;


    @Autowired
    public DoctorServiceImpl(DoctorRepository doctorRepository){
        this.doctorRepository = doctorRepository;
    }


    @Override
    public List<Doctor> findAll() {
        return doctorRepository.findAll();
    }

    @Override
    public void save(Doctor doctor) {
        doctorRepository.save(doctor);

    }

    @Override
    public void deleteById(int id) {
        doctorRepository.deleteById(id);
    }

    @Override
    public Doctor findById(int id) {

        Optional<Doctor> result = doctorRepository.findById(id);

        Doctor doctor = null;

        if(result.isPresent()){
            doctor = result.get();
        }else{
            throw new RuntimeException("Did not find doctor id - " + id);
        }

        return doctor;
    }
}
