package com.evooq.challenge.medicine.services;

import com.evooq.challenge.medicine.error.InputParseException;
import com.evooq.challenge.medicine.model.ClinicInput;
import com.evooq.challenge.medicine.model.ClinicStatus;
import com.evooq.challenge.medicine.model.Medicine;
import com.evooq.challenge.medicine.model.PatientState;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class ClinicApplicationImpl implements ClinicApplication {

    public ClinicStatus process(String input) throws InputParseException {
        ClinicInput  clinicInput =  getClinicInput(input);
        ClinicStatus clinicStatus = processPatients(clinicInput);

        return clinicStatus;
    }


    private ClinicInput  getClinicInput(String input) throws InputParseException {

        Pattern allowedChars = Pattern.compile("[a-zA-Z\\d,]+");
        var matcher = allowedChars.matcher(input);

        if (!matcher.matches()) {
            throw new InputParseException("Input error: Non allowed characters present.");
        }

        String[] keys = input.split(",");
        ClinicInput clinicInput = new ClinicInput();
        boolean alreadyParsedMedecine = false;

        for (int i = 0; i < keys.length; i++) {
            // check for patients
            var key = keys[i];

            if (PatientState.ALLOWED_STATES_KEYS.contains(key)) {

                if (alreadyParsedMedecine) {
                    throw new InputParseException("Input error: Patient keyword placed after medicine (" + key + ").");
                }

            } else if (Medicine.ALLOWED_MEDICINE_KEYS.contains(key)) {
                alreadyParsedMedecine = true;

            } else {
                throw new InputParseException("Input error: Invalid keyword (" + key + ").");
            }

        }

        return clinicInput;
    }

    private ClinicStatus processPatients(ClinicInput clinicInput) {
        return  new ClinicStatus();
    }

}
