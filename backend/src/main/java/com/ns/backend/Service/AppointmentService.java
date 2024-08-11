package com.ns.backend.Service;

import com.ns.backend.Entity.Appointment;

import java.sql.Time;
import java.util.Date;
import java.util.List;

public interface AppointmentService {

    List<Appointment> findAll();
    void save(Integer patientId, Integer doctorId, Date appointmentDate, Time appointmentTime);
    void deleteById(int id);
    Appointment findById(int id);
}
