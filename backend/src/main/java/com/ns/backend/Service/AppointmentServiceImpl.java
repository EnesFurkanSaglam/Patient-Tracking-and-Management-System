package com.ns.backend.Service;

import com.ns.backend.Dao.AppointmentRepository;
import com.ns.backend.Dao.DoctorRepository;
import com.ns.backend.Dao.PatientRepository;
import com.ns.backend.Entity.Appointment;
import com.ns.backend.Entity.Doctor;
import com.ns.backend.Entity.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class AppointmentServiceImpl implements AppointmentService {

    private AppointmentRepository appointmentRepository;
    private DoctorRepository doctorRepository;
    private PatientRepository patientRepository;

    @Autowired
    AppointmentServiceImpl(AppointmentRepository appointmentRepository,DoctorRepository doctorRepository, PatientRepository patientRepository){
        this.appointmentRepository = appointmentRepository;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
    }



    @Override
    public List<Appointment> findAll() {
        return appointmentRepository.findAll();
    }




    @Override
    public void save(Integer patientId, Integer doctorId, Date appointmentDate, Time appointmentTime) {

        Appointment appointment = new Appointment();

        Optional<Patient> optionalPatient = patientRepository.findById(patientId);

        if (optionalPatient.isPresent()) {
            Patient patient = optionalPatient.get();
            appointment.setPatient(patient);

        } else {

            throw new RuntimeException("Patient not found with id: " + patientId);
        }


        Optional<Doctor> optionalDoctor = doctorRepository.findById(doctorId);

        if (optionalDoctor.isPresent()) {
            Doctor doctor = optionalDoctor.get();
            appointment.setDoctor(doctor);

        } else {

            throw new RuntimeException("Doctor not found with id: " + doctorId);
        }


        appointment.setAppointmentDate(appointmentDate);
        appointment.setAppointmentTime(appointmentTime);

        appointmentRepository.save(appointment);

    }



    @Override
    public void deleteById(int id) {
        appointmentRepository.deleteById(id);

    }

    @Override
    public Appointment findById(int id) {

        Optional<Appointment> result = appointmentRepository.findById(id);

        Appointment appointment = null;

        if (result.isPresent()){
            appointment = result.get();
        }else{
            throw new RuntimeException("Did not find appointment  id - " + id);
        }
        return appointment;
    }

    @Override
    public List<Appointment> findByDoctorId(int doctorId) {

        List<Appointment> appointmentList = appointmentRepository.findAll();
        List<Appointment> appointments = new ArrayList<>();

        for (Appointment appointment : appointmentList){
            if (appointment.getDoctor().getDoctorId() == doctorId){
                appointments.add(appointment);
            }
        }
        if (!appointments.isEmpty()){
            return appointments;
        }
        throw new RuntimeException("Did not find any Appointments ");
    }
}
