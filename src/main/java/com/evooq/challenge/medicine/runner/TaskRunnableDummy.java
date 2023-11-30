package com.evooq.challenge.medicine.runner;

import com.evooq.challenge.medicine.error.InputParseException;
import com.evooq.challenge.medicine.model.ClinicStatus;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;


/* This is an empty implementation only used for Tests */
@Component
@Profile("test")
public class TaskRunnableDummy implements TaskRunnable {

    @Override
    public void run(String... args) {
        // Does nothing
    }

    @Override
    public void displayError(InputParseException ex) {
        throw new NotImplementedException("this method should not be called");
    }

    @Override
    public void displayStatus(ClinicStatus clinicStatus) {
        throw new NotImplementedException("this method should not be called");
    }

    @Override
    public void verifyInput(String[] args) {
        throw new NotImplementedException("this method should not be called");
    }
}
