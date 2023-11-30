package com.evooq.challenge.medicine.services;

import com.evooq.challenge.medicine.error.InputParseException;
import com.evooq.challenge.medicine.model.ClinicStatus;

public interface ClinicApplication {
    ClinicStatus process(String args)  throws InputParseException;
}
