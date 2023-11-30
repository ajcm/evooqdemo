package com.evooq.challenge.medicine.runner;

import com.evooq.challenge.medicine.error.ClinicException;
import com.evooq.challenge.medicine.error.InputParseException;
import com.evooq.challenge.medicine.model.ClinicInput;

public interface TaskRunnable {
    void run(String... args) throws ClinicException;

    void displayError(ClinicException ex);

    void displayStatus(int fever, int healthy, int diabetes, int tuberculosis, int dead);
}
