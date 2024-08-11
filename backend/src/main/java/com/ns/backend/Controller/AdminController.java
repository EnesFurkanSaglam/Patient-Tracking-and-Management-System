package com.ns.backend.Controller;


import ch.qos.logback.core.model.Model;
import com.ns.backend.Entity.Appointment;
import com.ns.backend.Entity.Doctor;
import com.ns.backend.Entity.Patient;
import com.ns.backend.Service.AppointmentService;
import com.ns.backend.Service.DoctorService;
import com.ns.backend.Service.PatientService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {


    private PatientService patientService;
    private DoctorService doctorService;
    private AppointmentService appointmentService;

    public AdminController(PatientService patientService, DoctorService doctorService,AppointmentService appointmentService) {
        this.patientService = patientService;
        this.doctorService = doctorService;
        this.appointmentService = appointmentService;
    }


    @GetMapping("/list-patient")
    @ResponseBody
    public List<Patient> listPatients(){
        return patientService.findAll();
    }

    @PostMapping("/save-patient")
    public ResponseEntity<String> savePatient(@RequestBody Patient patient) {
        patientService.save(patient);

        return ResponseEntity.ok("Patient saved successfully");
    }

//    @PostMapping("/delete-patient")
//    public ResponseEntity<String> deletePatient(@RequestParam("patientId") int id) {
//
//        patientService.deleteById(id);
//
//        return ResponseEntity.ok("Patient deleted successfully");
//    }



    @GetMapping("/list-doctor")
    @ResponseBody
    public List<Doctor> listDoctor(){
        return doctorService.findAll();
    }

    @PostMapping("/save-doctor")
    public ResponseEntity<String> saveDoctor(@RequestBody Doctor doctor){
        doctorService.save(doctor);
        return ResponseEntity.ok("Doctor saved succesfully");
    }

//    @PostMapping("/delete-doctor")
//    public ResponseEntity<String> deleteDoctor(@RequestParam("doctorId")int id){
//        doctorService.deleteById(id);
//
//        return ResponseEntity.ok("Doctor deleted successfully");
//    }



    @GetMapping("/list-appointment")
    @ResponseBody
    public List<Appointment> listAppointment(){
        return appointmentService.findAll();
    }




    @PostMapping("/save-appointment")
    public ResponseEntity<String> saveAppointment(
            @RequestParam Integer patientId,
            @RequestParam Integer doctorId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate appointmentDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime appointmentTime) {


        LocalDateTime localDateTime = appointmentDate.atStartOfDay();
        Date sqlDate = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        Time sqlTime = Time.valueOf(appointmentTime);

        appointmentService.save(patientId, doctorId, sqlDate, sqlTime);

        return ResponseEntity.ok("Appointment saved successfully");
    }


//    @PostMapping("/delete-appointment")
//    public ResponseEntity<String> deleteAppointment(@RequestParam("appointmentId") int id){
//        appointmentService.deleteById(id);
//        return ResponseEntity.ok("Appointment deleted successfully");
//    }



}
