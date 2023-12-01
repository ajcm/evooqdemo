package com.evooq.challenge.medicine.model;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;

public class ClinicInput {

    //order does not matter
    List<Patient> patients = new LinkedList<>();

    //must keep order and unique values
    LinkedHashSet<Medicine> medicines = new LinkedHashSet<>();

    public static ClinicInput of(List<Patient> patients) {
        ClinicInput clinicInput = new ClinicInput();
        clinicInput.setPatients(patients);

        return clinicInput;
    }

    public static ClinicInput of(List<Patient> patients, List<Medicine> medicineList) {
        ClinicInput clinicInput = new ClinicInput();
        clinicInput.setPatients(patients);

        medicineList.forEach(m -> clinicInput.getMedicines().add(m));

        return clinicInput;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

    public LinkedHashSet<Medicine> getMedicines() {
        return medicines;
    }

    @Override
    public String toString() {

        //StringBuilder stringBuilder = new
        var listOfPatients = patients.stream().map(p -> p.toString()).toList();
        var listOfMedicinces = medicines.stream().map(p -> p.toString()).toList();

        String output = String.join(",", listOfPatients) + "," + String.join(",", listOfMedicinces);


        return output;
    }
}
