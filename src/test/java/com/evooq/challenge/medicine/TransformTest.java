package com.evooq.challenge.medicine;

import com.evooq.challenge.medicine.error.InputParseException;
import com.evooq.challenge.medicine.input.Transform;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
public class TransformTest {

    @Autowired
    Transform transform;

    @Test
    public void testSemantics() {
        InputParseException exception;

        //invalid keyword
        exception = Assertions.assertThrows(InputParseException.class,
                () -> transform.getClinicInput("banana"));
        Assertions.assertEquals("Input error: Invalid keyword (banana).", exception.getMessage());

        //invalid keyword
        exception = Assertions.assertThrows(InputParseException.class,
                () -> transform.getClinicInput("PPP"));
        Assertions.assertEquals("Input error: Invalid keyword (PPP).", exception.getMessage());

        //invalid keyword order
        exception = Assertions.assertThrows(InputParseException.class,
                () -> transform.getClinicInput("P,P,I,X"));
        Assertions.assertEquals("Input error: Patient keyword placed after medicine (X).", exception.getMessage());
    }


}
