package com.ns.backend.Service;

import com.ns.backend.Entity.LabResult;


import java.util.List;

public interface LabResultService {

    List<LabResult> getByMedicalReportId(int medicalReportId);
    void save (Integer medicalReportId,String url);
    void deleteById(int id);
    LabResult findById(int id);

}
