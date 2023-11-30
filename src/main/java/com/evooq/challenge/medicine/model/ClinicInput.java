package com.evooq.challenge.medicine.model;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;

public class ClinicInput {

    //order does not matter
    List<Patient> patients = new LinkedList<>();

    //must keep order and unique values
    LinkedHashSet<Medicine> medicines = new LinkedHashSet<>();



    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

    public LinkedHashSet<Medicine> getMedicines() {
        return medicines;
    }

    public static ClinicInput of(List<Patient> patients) {
        ClinicInput clinicInput = new ClinicInput();
        clinicInput.setPatients(patients);

        return clinicInput;
    }
}
