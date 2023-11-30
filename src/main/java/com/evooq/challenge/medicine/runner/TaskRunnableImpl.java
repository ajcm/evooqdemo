package com.evooq.challenge.medicine.runner;

import com.evooq.challenge.medicine.error.ClinicException;
import com.evooq.challenge.medicine.error.InputParseException;
import com.evooq.challenge.medicine.error.ProcessingException;
import com.evooq.challenge.medicine.model.ClinicInput;
import com.evooq.challenge.medicine.model.ClinicStatus;
import com.evooq.challenge.medicine.model.Medicine;
import com.evooq.challenge.medicine.model.Patient;
import com.evooq.challenge.medicine.services.ClinicApplication;
import com.evooq.challenge.medicine.input.Transform;
import com.evooq.challenge.medicine.input.Validation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("!test")
public class TaskRunnableImpl implements TaskRunnable {
    Logger LOG = LoggerFactory.getLogger(TaskRunnableImpl.class);


    @Autowired
    private ClinicApplication clinicApplication;

    @Autowired
    private Transform transform;

    @Autowired
    private Validation validation;

    @Override
    public void run(String... args) {

        try {

            validation.verifyInput(args);

            String input = args[0];
            LOG.debug("receive input {}", input);
            ClinicInput clinicInput = transform.getClinicInput(input);
            ClinicStatus clinicStatus = clinicApplication.process(clinicInput);

            LOG.debug("processed output {}", clinicStatus);

            var fever = clinicStatus.get(Patient.F);
            var healthy = clinicStatus.get(Patient.H);
            var diabetes = clinicStatus.get(Patient.D);
            var tuberculosis = clinicStatus.get(Patient.T);
            var dead = clinicStatus.get(Patient.X);

            displayStatus(fever, healthy, diabetes, tuberculosis, dead);

        } catch (InputParseException | ProcessingException ex) {
            displayError(ex);
            LOG.debug(ex.getMessage());
            LOG.debug("Error processing input " + ex.getMessage(), ex);
        }

    }



    @Override
    public void displayError(ClinicException ex) {
        System.err.println(ex.getMessage());
        System.err.println("ex: P0,P1..,Pn[,M0,M1,..Mn]");
        System.err.print("where Pn: Patient state is one of " + Patient.ALLOWED_STATES_KEYS);
        System.err.println(" and Mn: Medicine is one of " + Medicine.ALLOWED_MEDICINE_KEYS);
    }







    @Override
    public void displayStatus(int fever, int healthy, int diabetes, int tuberculosis, int dead) {
        System.out.printf("F:%d,H:%d,D:%d,T:%d,X:%d%n", fever, healthy, diabetes, tuberculosis, dead);
    }
}
