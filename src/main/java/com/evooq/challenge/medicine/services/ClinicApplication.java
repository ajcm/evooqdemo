package com.evooq.challenge.medicine.services;

import com.evooq.challenge.medicine.error.ProcessingException;
import com.evooq.challenge.medicine.model.ClinicInput;
import com.evooq.challenge.medicine.model.ClinicStatus;

public interface ClinicApplication {
    ClinicStatus process(ClinicInput clinicInput) throws ProcessingException;
}
