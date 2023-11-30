package com.evooq.challenge.medicine.model;

import java.util.Set;

public enum Patient {

    F("Fever"),
    H("Healthy"),
    D("Diabetes"),
    T("Tuberculosis"),
    X("Dead");


    public static Set<String> ALLOWED_STATES_KEYS = Set.of(F.name(), H.name(), D.name(), T.name(), X.name());
    private String description;

    Patient(String description) {
        description = description;
    }

}
