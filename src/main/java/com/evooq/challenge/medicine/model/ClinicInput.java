package com.evooq.challenge.medicine.model;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;

public class ClinicInput {

    //order does not matter
    private final List<Patient> patients = new LinkedList<>();

    //must keep order and unique values
    private final LinkedHashSet<Medicine> medicines = new LinkedHashSet<>();

    public static ClinicInput of(List<Patient> patients) {
        ClinicInput clinicInput = new ClinicInput();
        clinicInput.patients.addAll(patients);

        return clinicInput;
    }

    public static ClinicInput of(List<Patient> patients, List<Medicine> medicineList) {
        ClinicInput clinicInput = new ClinicInput();
        clinicInput.patients.addAll(patients);
        medicineList.forEach(m -> clinicInput.getMedicines().add(m));

        return clinicInput;
    }

    public List<Patient> getPatients() {
        return patients;
    }


    public LinkedHashSet<Medicine> getMedicines() {
        return medicines;
    }

    @Override
    public String toString() {

        var listOfPatients = patients.stream().map(Enum::toString).toList();
        var listOfMedicines = medicines.stream().map(Enum::toString).toList();

        return String.join(",", listOfPatients) + "," + String.join(",", listOfMedicines);
    }
}
