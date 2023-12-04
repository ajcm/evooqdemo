package com.evooq.challenge.medicine.input;

import com.evooq.challenge.medicine.error.InputParseException;
import com.evooq.challenge.medicine.model.ClinicInput;
import com.evooq.challenge.medicine.model.Medicine;
import com.evooq.challenge.medicine.model.Patient;
import org.springframework.stereotype.Component;

@Component
public class Transform {

    public ClinicInput getClinicInput(String input) throws InputParseException {

        String[] keys = input.split("[, ]");
        ClinicInput clinicInput = new ClinicInput();
        boolean alreadyParsedMedicine = false;

        for (int i = 0; i < keys.length; i++) {
            // check for patients
            var key = keys[i];

            if (Patient.ALLOWED_STATES_KEYS.contains(key)) {

                if (alreadyParsedMedicine) {
                    throw new InputParseException("Input error: Patient keyword placed after medicine (" + key + ").");
                }
                clinicInput.getPatients().add(Patient.valueOf(key));

            } else if (Medicine.ALLOWED_MEDICINE_KEYS.contains(key)) {
                alreadyParsedMedicine = true;
                clinicInput.getMedicines().add(Medicine.valueOf(key));

            } else {
                throw new InputParseException("Input error: Invalid keyword (" + key + ").");
            }
        }

        return clinicInput;
    }

}
