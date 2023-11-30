package com.evooq.challenge.medicine.services;

import com.evooq.challenge.medicine.error.ProcessingException;
import com.evooq.challenge.medicine.model.ClinicInput;
import com.evooq.challenge.medicine.model.ClinicStatus;
import com.evooq.challenge.medicine.model.Medicine;
import com.evooq.challenge.medicine.model.Patient;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;

@Service
public class ClinicApplicationImpl implements ClinicApplication {

    public ClinicStatus process(ClinicInput clinicInput) throws ProcessingException {

        if (clinicInput.getPatients().isEmpty()) {
            //nothing to do
            return ClinicStatus.empty();
        }else if (clinicInput.getMedicines().isEmpty()){
            //send the input
            return processPatients(clinicInput.getPatients());
        }else {
            return processMedicines(clinicInput.getPatients(),clinicInput.getMedicines());
        }

    }

    private ClinicStatus processMedicines(List<Patient> patients, LinkedHashSet<Medicine> medicines) {
        return new ClinicStatus();
    }


    private ClinicStatus processPatients(List<Patient> patients) {
        ClinicStatus clinicStatus = new ClinicStatus();

        for (Patient p : patients){
            clinicStatus.add(p);
        }

        return clinicStatus;
    }

}
