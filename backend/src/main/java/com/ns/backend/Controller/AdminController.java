package com.ns.backend.Controller;


import com.ns.backend.Entity.*;
import com.ns.backend.Service.*;
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
    private MedicalReportService medicalReportService;
    private LabResultService labResultService;
    private AdminService adminService;

    public AdminController(PatientService patientService, DoctorService doctorService, AppointmentService appointmentService, MedicalReportService medicalReportService, LabResultService labResultService, AdminService adminService) {
        this.patientService = patientService;
        this.doctorService = doctorService;
        this.appointmentService = appointmentService;
        this.medicalReportService = medicalReportService;
        this.labResultService = labResultService;
        this.adminService = adminService;
    }



    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String name, @RequestParam int id) {

        Admin admin = adminService.findByName(name);

        if (admin.getAdminId() == id){
            return ResponseEntity.ok("Admin found ");
        }
        else {
            return  ResponseEntity.ok("Admin not found");
        }

    }


    @GetMapping("/list-patient")
    @ResponseBody
    public List<Patient> listPatients() {
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
    public List<Doctor> listDoctor() {
        return doctorService.findAll();
    }

    @PostMapping("/save-doctor")
    public ResponseEntity<String> saveDoctor(@RequestBody Doctor doctor) {
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
    public List<Appointment> listAppointment() {
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


    @GetMapping("/list-medical-report")
    @ResponseBody
    public List<MedicalReport> listMedicalReport(@RequestParam int patientId) {
        return medicalReportService.getByPatientId(patientId);
    }


    @PostMapping("/save-medical-report")
    public ResponseEntity<String> saveMedicalReport(
            @RequestParam Integer patientId,
            @RequestParam String reportContent,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate reportDate) {

        LocalDateTime localDateTime = reportDate.atStartOfDay();
        Date sqlDate = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());

        medicalReportService.save(sqlDate, reportContent, patientId);
        return ResponseEntity.ok("Medical Report saved succesfully");
    }

//    @PostMapping("/delete-medical-report")
//    public ResponseEntity<String> deleteMedicalReport() {
//
//        return ResponseEntity.ok("Medical Report deleted successfully");
//    }

    @GetMapping("/list-lab-result")
    @ResponseBody
    public List<LabResult> listLabResult(@RequestParam int medicalReportId) {

        return labResultService.getByMedicalReportId(medicalReportId);
    }

    @PostMapping("/save-lab-result")
    public ResponseEntity<String> saveLabResult(
            @RequestParam Integer medicalReportId,
            @RequestParam String url) {

        labResultService.save(medicalReportId,url);

        return ResponseEntity.ok("Lab Result saved succesfully");
    }


//    @PostMapping("/delete-lab-result")
//    public ResponseEntity<String> deleteLabResult() {
//        return ResponseEntity.ok("Lab Result deleted successfully");
//    }








}
