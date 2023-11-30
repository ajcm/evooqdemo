package com.evooq.challenge.medicine;

import com.evooq.challenge.medicine.error.InputParseException;
import com.evooq.challenge.medicine.runner.TaskRunnable;
import com.evooq.challenge.medicine.services.ClinicApplication;
import com.evooq.challenge.medicine.services.ClinicApplicationImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class ClinicApplicationTest {

    @Autowired
    private ClinicApplication clinicApplication;


    @Test
    public void testEmptyInput() {
        InputParseException exception;

        //bad chars
        exception = Assertions.assertThrows(InputParseException.class,
                () -> clinicApplication.process("ba???nana"));
        Assertions.assertEquals("Input error: Non allowed characters present.", exception.getMessage());

        //invalid keyword
        exception = Assertions.assertThrows(InputParseException.class,
                () -> clinicApplication.process("banana"));
        Assertions.assertEquals("Input error: Invalid keyword (banana).", exception.getMessage());

        //invalid keyword
        exception = Assertions.assertThrows(InputParseException.class,
                () -> clinicApplication.process("PPP"));
        Assertions.assertEquals("Input error: Invalid keyword (PPP).", exception.getMessage());

        //invalid keyword order
        exception = Assertions.assertThrows(InputParseException.class,
                () -> clinicApplication.process("P,P,I,X"));
        Assertions.assertEquals("Input error: Patient keyword placed after medicine (X).", exception.getMessage());

    }
}
