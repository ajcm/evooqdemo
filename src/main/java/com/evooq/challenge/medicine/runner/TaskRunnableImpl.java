package com.evooq.challenge.medicine.runner;

import com.evooq.challenge.medicine.error.InputParseException;
import com.evooq.challenge.medicine.model.ClinicStatus;
import com.evooq.challenge.medicine.model.Medicine;
import com.evooq.challenge.medicine.model.PatientState;
import com.evooq.challenge.medicine.services.ClinicApplication;
import org.apache.commons.lang3.StringUtils;
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

    @Override
    public void run(String... args) {

        try {
            verifyInput(args);
            String input = args[0];
            LOG.debug("receive input {}", input);
            ClinicStatus clinicStatus = clinicApplication.process(input);
            LOG.debug("processed output {}", clinicStatus);

        } catch (InputParseException ex) {
            displayError(ex);
            LOG.error(ex.getMessage());
            LOG.debug("stacktrace", ex);
        }

    }

    @Override
    public void displayStatus(ClinicStatus clinicStatus) {
        System.out.println();

    }

    @Override
    public void displayError(InputParseException ex) {
        System.err.println(ex.getMessage());
        System.err.println("ex: P0,P1..,Pn[,M0,M1,..Mn]");
        System.err.println("where Pn: Patient state is one of " + PatientState.ALLOWED_STATES_KEYS);
        System.err.println("and Mn: Medicine is one of " + Medicine.ALLOWED_MEDICINE_KEYS);
    }

    @Override
    public void verifyInput(String[] args) throws InputParseException {

        if (args == null || args.length < 1) {
            throw new InputParseException("Input error: Expecting one argument.");
        }

        if (args.length > 1) {
            throw new InputParseException("Input error: Expecting only one argument.");
        }

        var input = StringUtils.strip(args[0]);

        //shouldn't be necessary when getting values from console args
        if (StringUtils.isBlank(input)) {
            throw new InputParseException("Input error: argument is empty.");
        }
    }
}
