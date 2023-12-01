package com.evooq.challenge.medicine;

import com.evooq.challenge.medicine.error.InputParseException;
import com.evooq.challenge.medicine.input.Transform;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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


    @Test
    public void testConversion() throws InputParseException {
        var inputData1 = transform.getClinicInput("D,X,F,T,H,H");
        Assertions.assertEquals("D,X,F,T,H,H,", inputData1.toString());

        var inputData2 = transform.getClinicInput("D,X,F,T,H,H,As,As,I,I,I");
        Assertions.assertEquals("D,X,F,T,H,H,As,I", inputData2.toString());

        var inputData3 = transform.getClinicInput("X,As,As,I,I,I");
        Assertions.assertEquals("X,As,I", inputData3.toString());

        var inputData4 = transform.getClinicInput("As,As,I,I,I");
        Assertions.assertEquals(",As,I", inputData4.toString());

        var inputData5 = transform.getClinicInput("X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,As,As,I,I,I");
        Assertions.assertEquals("X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,As,I", inputData5.toString());

        var inputData6 = transform.getClinicInput("X,T,T,X,X,X,T,T,X,X,T,T,T,X,X,As,I");
        Assertions.assertEquals("X,T,T,X,X,X,T,T,X,X,T,T,T,X,X,As,I", inputData6.toString());
    }

}
