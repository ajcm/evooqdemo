package com.evooq.challenge.medicine.model;

import java.util.HashMap;
import java.util.Map;

public class ClinicStatus {
    Map<Patient, Integer> status = new HashMap<>();

    public ClinicStatus() {
    }

    public ClinicStatus(int fever, int healthy, int diabetes, int tuberculosis, int dead) {
        set(Patient.F, fever);
        set(Patient.H, healthy);
        set(Patient.D, diabetes);
        set(Patient.T, tuberculosis);
        set(Patient.X, dead);
    }

    public static ClinicStatus empty() {
        return new ClinicStatus(0, 0, 0, 0, 0);
    }

    public int get(Patient patient) {
        if (!status.containsKey(patient)) {
            return 0;
        }
        return status.get(patient);
    }

    private void set(Patient state, int number) {
        status.put(state, number);
    }

    public void add(Patient patient) {
        set(patient, get(patient) + 1);
    }

    @Override
    public String toString() {
        return String.format("%d-%d-%d-%d-%d",
                get(Patient.F),
                get(Patient.H),
                get(Patient.D),
                get(Patient.T),
                get(Patient.X));
    }
}
