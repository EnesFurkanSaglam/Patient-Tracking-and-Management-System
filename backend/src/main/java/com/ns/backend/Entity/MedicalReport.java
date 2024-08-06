package com.ns.backend.Entity;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "medical_reports")
public class MedicalReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reportId;

    @Column(name = "report_date")
    private Date reportDate;

    @Column(name = "report_content")
    private String reportContent;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    public MedicalReport() {
    }

    public MedicalReport(Date reportDate, String reportContent, Patient patient) {
        this.reportDate = reportDate;
        this.reportContent = reportContent;
        this.patient = patient;
    }

    public MedicalReport(Integer reportId, Date reportDate, String reportContent, Patient patient) {
        this.reportId = reportId;
        this.reportDate = reportDate;
        this.reportContent = reportContent;
        this.patient = patient;
    }

    public Integer getReportId() {
        return reportId;
    }

    public void setReportId(Integer reportId) {
        this.reportId = reportId;
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    public String getReportContent() {
        return reportContent;
    }

    public void setReportContent(String reportContent) {
        this.reportContent = reportContent;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    @Override
    public String toString() {
        return "MedicalReport{" +
                "reportId=" + reportId +
                ", reportDate=" + reportDate +
                ", reportContent='" + reportContent + '\'' +
                ", patient=" + patient +
                '}';
    }
}
