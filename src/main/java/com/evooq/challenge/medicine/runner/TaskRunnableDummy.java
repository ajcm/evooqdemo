package com.evooq.challenge.medicine.runner;

import com.evooq.challenge.medicine.error.ClinicException;
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
    public void displayError(ClinicException ex) {
        throw new NotImplementedException("this method should not be called");
    }

    @Override
    public void displayStatus(int fever, int healthy, int diabetes, int tuberculosis, int dead) {
        throw new NotImplementedException("this method should not be called");

    }
}
