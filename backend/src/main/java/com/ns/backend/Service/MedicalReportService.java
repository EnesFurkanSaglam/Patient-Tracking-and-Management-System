package com.ns.backend.Service;

import com.ns.backend.Entity.MedicalReport;

import java.util.Date;
import java.util.List;

public interface MedicalReportService {

    List<MedicalReport> getByPatientId(int patientId);
    void save(Date reportDate, String reportContent, Integer patientId);
    void deleteById(int id);
    MedicalReport findById(int id);


}
