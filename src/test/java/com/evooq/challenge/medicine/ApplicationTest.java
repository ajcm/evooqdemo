package com.evooq.challenge.medicine;

import com.evooq.challenge.medicine.error.InputParseException;
import com.evooq.challenge.medicine.error.ProcessingException;
import com.evooq.challenge.medicine.input.Transform;
import com.evooq.challenge.medicine.model.ClinicInput;
import com.evooq.challenge.medicine.model.ClinicStatus;
import com.evooq.challenge.medicine.services.ClinicApplication;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApplicationTest {

    @Autowired
    Transform transform;

    @Autowired
    ClinicApplication clinicApplication;

    @Test
    void contextLoads() {
    }


    @Test
    void testFlow() throws InputParseException, ProcessingException {

        String input = "D,D";
        ClinicStatus clinicStatus = getClinicStatus(input);
        Assertions.assertEquals("0-0-0-0-2", clinicStatus.toString());

        input = "F,P";
        clinicStatus = getClinicStatus(input);
        Assertions.assertEquals("0-1-0-0-0", clinicStatus.toString());

        input = "T,F,D,An,I";
        clinicStatus = getClinicStatus(input);
        Assertions.assertEquals("2-0-1-0-0", clinicStatus.toString());


    }

    private ClinicStatus getClinicStatus(String input) throws InputParseException, ProcessingException {
        ClinicInput clinicInput = transform.getClinicInput(input);
        ClinicStatus clinicStatus = clinicApplication.process(clinicInput);
        return clinicStatus;
    }


}
