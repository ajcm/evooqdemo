package com.evooq.challenge.medicine.model;

import java.util.Set;

public enum Medicine {
    As("Aspirin"),
    An("Antibiotic"),
    I("Insulin"),
    P("Paracetamol"),
    Fsm("Flying Spaghetti Monster"); // special case, not available as input

    public static Set<String> ALLOWED_MEDICINE_KEYS = Set.of(
            As.name(),
            An.name(),
            I.name(),
            P.name());
    private final String description;

    Medicine(String description) {
        this.description = description;
    }
}
