package com.ns.backend.Controller;

import com.ns.backend.Entity.Appointment;
import com.ns.backend.Entity.Doctor;
import com.ns.backend.Entity.LabResult;
import com.ns.backend.Entity.MedicalReport;
import com.ns.backend.Service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/doctor")
public class DoctorController {

    private PatientService patientService;
    private DoctorService doctorService;
    private AppointmentService appointmentService;
    private MedicalReportService medicalReportService;
    private LabResultService labResultService;


    public DoctorController(PatientService patientService, DoctorService doctorService, AppointmentService appointmentService, MedicalReportService medicalReportService, LabResultService labResultService) {
        this.patientService = patientService;
        this.doctorService = doctorService;
        this.appointmentService = appointmentService;
        this.medicalReportService = medicalReportService;
        this.labResultService = labResultService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String name, @RequestParam int id, HttpSession session) {
        Doctor doctor = doctorService.findById(id);

        if (doctor.getDoctorName().equals(name)) {
            session.setAttribute("doctor", doctor); // Store doctor in session
            return ResponseEntity.ok("Doctor found");
        } else {
            return ResponseEntity.ok("Doctor not found");
        }
    }

    @GetMapping("/list-appointment")
    @ResponseBody
    public ResponseEntity<List<Appointment>> listAppointment(HttpSession session) {
        Doctor theDoctor = (Doctor) session.getAttribute("doctor");

        if (theDoctor != null) {
            List<Appointment> appointments = appointmentService.findByDoctorId(theDoctor.getDoctorId());
            return ResponseEntity.ok(appointments);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }


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
