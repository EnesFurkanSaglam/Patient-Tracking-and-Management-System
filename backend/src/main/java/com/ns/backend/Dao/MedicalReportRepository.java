package com.ns.backend.Dao;

import com.ns.backend.Entity.MedicalReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalReportRepository extends JpaRepository<MedicalReport,Integer> {

}
