package com.evooq.challenge.medicine.model;

import java.util.Set;

public enum PatientState {

    F("Fever"),
    H("Healthy"),
    D("Diabetes"),
    T("Tuberculosis"),
    X("Dead");


    private String description;

    public static Set<String> ALLOWED_STATES_KEYS = Set.of(F.name(), H.name(), D.name(), T.name(),X.name());

    PatientState(String description) {
        description = description;
    }

}
