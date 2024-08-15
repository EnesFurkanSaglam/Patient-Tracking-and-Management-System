package com.ns.backend.Service;

import com.ns.backend.Dao.LabResultRepository;
import com.ns.backend.Dao.MedicalReportRepository;
import com.ns.backend.Entity.LabResult;
import com.ns.backend.Entity.MedicalReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class LabResultServiceImpl implements LabResultService {


    private MedicalReportRepository medicalReportRepository;
    private LabResultRepository labResultRepository;


    @Autowired
    public LabResultServiceImpl(LabResultRepository labResultRepository, MedicalReportRepository medicalReportRepository) {
        this.labResultRepository = labResultRepository;
        this.medicalReportRepository = medicalReportRepository;
    }

    @Override
    public List<LabResult> getByMedicalReportId(int medicalReportId) {
        List<LabResult> labResultList =  labResultRepository.findAll();
        List<LabResult> labResults = new ArrayList<>();

        for (LabResult labResult : labResultList){
            if (labResult.getMedicalReport().getReportId() == medicalReportId){
                labResults.add(labResult);
            }
        }
        if (!labResults.isEmpty()){
            return labResults;
        }
        throw new RuntimeException("Did not find Lab Result by Medical Report id - " + medicalReportId);
    }

    @Override
    public void save(Integer medicalReportId, String url) {

        LabResult labResult = new LabResult();

        Optional<MedicalReport> optionalMedicalReport = medicalReportRepository.findById(medicalReportId);

        if(optionalMedicalReport.isPresent()){
            MedicalReport medicalReport = optionalMedicalReport.get();
            labResult.setMedicalReport(medicalReport);
        }else{
            throw new RuntimeException("Medical Report not found with id: " + medicalReportId);
        }
        labResult.setUrl(url);

        labResultRepository.save(labResult);

    }

    @Override
    public void deleteById(int id) {
        labResultRepository.deleteById(id);

    }

    @Override
    public LabResult findById(int id) {

        Optional<LabResult> result = labResultRepository.findById(id);

        LabResult labResult = null;

        if (result.isPresent()){
            labResult = result.get();

        }else{
            throw new RuntimeException("Did not find Lab result id - " + id);
        }

        return labResult;

    }
}
