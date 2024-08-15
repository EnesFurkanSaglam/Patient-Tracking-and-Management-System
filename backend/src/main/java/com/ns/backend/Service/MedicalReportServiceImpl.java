package com.ns.backend.Service;

import com.ns.backend.Dao.MedicalReportRepository;
import com.ns.backend.Dao.PatientRepository;
import com.ns.backend.Entity.MedicalReport;
import com.ns.backend.Entity.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class MedicalReportServiceImpl implements MedicalReportService {



    private MedicalReportRepository medicalReportRepository;
    private PatientRepository patientRepository;

    @Autowired
    public MedicalReportServiceImpl(MedicalReportRepository medicalReportRepository,PatientRepository patientRepository){
        this.medicalReportRepository = medicalReportRepository;
        this.patientRepository = patientRepository;
    }


    @Override
    public List<MedicalReport> getByPatientId(int patientId) {

        List<MedicalReport> medicalReportList = medicalReportRepository.findAll();
        List<MedicalReport> medicalReports = new ArrayList<>();

        for (MedicalReport medicalReport : medicalReportList){
            if (medicalReport.getPatient().getPatientId() == patientId){
                medicalReports.add(medicalReport);
            }
        }
        if (!medicalReports.isEmpty()){
            return medicalReports;
        }
        throw new RuntimeException("Did not find Report by patient id - " + patientId);
    }

    @Override
    public void save(Date reportDate,String reportContent,Integer patientId){
        MedicalReport medicalReport = new MedicalReport();

        Optional<Patient> optionalPatient = patientRepository.findById(patientId);

        if (optionalPatient.isPresent()) {
            Patient patient = optionalPatient.get();
            medicalReport.setPatient(patient);

        } else {

            throw new RuntimeException("Patient not found with id: " + patientId);
        }

        medicalReport.setReportContent(reportContent);
        java.sql.Date sqlDate = new java.sql.Date(reportDate.getTime());
        medicalReport.setReportDate(sqlDate);

        medicalReportRepository.save(medicalReport);
    }

    @Override
    public void deleteById(int id) {
        medicalReportRepository.deleteById(id);
    }

    @Override
    public MedicalReport findById(int id) {

        Optional<MedicalReport> result = medicalReportRepository.findById(id);

        MedicalReport medicalReport = null;

        if (result.isPresent()){
            medicalReport = result.get();

        }else{
            throw new RuntimeException("Did not find Medical Report id - " + id);
        }

        return medicalReport;


    }
}
