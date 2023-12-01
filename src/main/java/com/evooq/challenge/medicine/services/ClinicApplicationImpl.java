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

    private static void runMedicineList(LinkedHashSet<Medicine> medicines, ClinicStatus clinicStatus) {
        List<Medicine> listOfMedicine = medicines.stream().toList();
        for (Medicine m : listOfMedicine) {

            //Aspirine cures Fever
            if (m == Medicine.As) {
                int sick = clinicStatus.get(Patient.F);
                clinicStatus.set(Patient.F, 0);
                clinicStatus.inc(Patient.H, sick);
            }

            //Antibiotic cures Tuberculosis
            if (m == Medicine.An) {
                int sick = clinicStatus.get(Patient.T);
                clinicStatus.set(Patient.T, 0);
                clinicStatus.inc(Patient.H, sick);
            }


            //Paracetamol cures Fever
            if (m == Medicine.P) {
                int sick = clinicStatus.get(Patient.F);
                clinicStatus.set(Patient.F, 0);
                clinicStatus.inc(Patient.H, sick);
            }
        }
    }

    public ClinicStatus process(ClinicInput clinicInput) throws ProcessingException {

        if (clinicInput.getPatients().isEmpty()) {
            //nothing to do
            return ClinicStatus.empty();
        } else {
            return processMedicines(clinicInput.getPatients(), clinicInput.getMedicines());
        }

    }

    private ClinicStatus processMedicines(List<Patient> patients, LinkedHashSet<Medicine> medicines) {

        ClinicStatus clinicStatus = get(patients);

        runMedicineList(medicines, clinicStatus);

        //Insulin + An: H -> F
        if (medicines.contains(Medicine.I) && medicines.contains(Medicine.An)) {
            int sick = clinicStatus.get(Patient.H);
            clinicStatus.set(Patient.H, 0);
            clinicStatus.inc(Patient.F, sick);
        }

        //Kill people with diabetes without I >:>
        if (!medicines.contains(Medicine.I)) {
            int sick = clinicStatus.get(Patient.D);
            clinicStatus.set(Patient.D, 0);
            clinicStatus.inc(Patient.X, sick);
        }

        //Paracetamol + As: -> :-X
        if (medicines.contains(Medicine.P) && medicines.contains(Medicine.As)) {
            int all = clinicStatus.countPatients();
            clinicStatus.reset();
            clinicStatus.set(Patient.X, all);
        }

        //Flying Spaghetti Monster

        return clinicStatus;
    }

    private ClinicStatus get(List<Patient> patients) {
        ClinicStatus clinicStatus = new ClinicStatus();
        patients.forEach(p -> clinicStatus.add(p));
        return clinicStatus;
    }

}
