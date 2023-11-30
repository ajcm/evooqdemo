package com.evooq.challenge.medicine.runner;

import com.evooq.challenge.medicine.error.InputParseException;
import com.evooq.challenge.medicine.model.ClinicStatus;

public interface TaskRunnable {
    void run(String... args);
    void verifyInput(String[] args) throws InputParseException;
    void displayError(InputParseException ex);
    void displayStatus(ClinicStatus clinicStatus);
}
