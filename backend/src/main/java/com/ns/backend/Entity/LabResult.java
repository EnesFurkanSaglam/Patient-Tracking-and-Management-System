package com.ns.backend.Entity;


import jakarta.persistence.*;

@Entity
@Table(name = "lab_results")
public class LabResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer labResultId;

    @ManyToOne
    @JoinColumn(name = "report_id")
    private MedicalReport medicalReport;

    @Column(name = "url")
    private String url;

    public LabResult() {
    }

    public LabResult(MedicalReport medicalReport, String url) {
        this.medicalReport = medicalReport;
        this.url = url;
    }

    public LabResult(Integer labResultId, MedicalReport medicalReport, String url) {
        this.labResultId = labResultId;
        this.medicalReport = medicalReport;
        this.url = url;
    }

    public Integer getLabResultId() {
        return labResultId;
    }

    public void setLabResultId(Integer labResultId) {
        this.labResultId = labResultId;
    }

    public MedicalReport getMedicalReport() {
        return medicalReport;
    }

    public void setMedicalReport(MedicalReport medicalReport) {
        this.medicalReport = medicalReport;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "LabResult{" +
                "labResultId=" + labResultId +
                ", medicalReport=" + medicalReport +
                ", url='" + url + '\'' +
                '}';
    }
}


