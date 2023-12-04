package com.evooq.challenge.medicine;

import com.evooq.challenge.medicine.error.InputParseException;
import com.evooq.challenge.medicine.input.Validation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ValidationTest {

    @Autowired
    Validation validation;

    @Test
    public void testVerifyInput() {
        InputParseException exception;

        //empty String
        exception = Assertions.assertThrows(InputParseException.class,
                () -> validation.verifyInput(null));
        Assertions.assertEquals("Input error: Expecting at least one argument.", exception.getMessage());

        //empty String
        exception = Assertions.assertThrows(InputParseException.class,
                () -> validation.verifyInput(new String[]{""}));
        Assertions.assertEquals("Input error: argument is empty.", exception.getMessage());

        exception = Assertions.assertThrows(InputParseException.class,
                () -> validation.verifyInput(new String[]{","}));
        Assertions.assertEquals("Input error: Bad input format.", exception.getMessage());


        exception = Assertions.assertThrows(InputParseException.class,
                () -> validation.verifyInput(new String[]{"",""}));
        Assertions.assertEquals("Input error: Bad input format.", exception.getMessage());

        exception = Assertions.assertThrows(InputParseException.class,
                () -> validation.verifyInput(new String[]{",,"}));
        Assertions.assertEquals("Input error: Bad input format.", exception.getMessage());

        exception = Assertions.assertThrows(InputParseException.class,
                () -> validation.verifyInput(new String[]{", ,"}));
        Assertions.assertEquals("Input error: Bad input format.", exception.getMessage());

        //bad chars
        exception = Assertions.assertThrows(InputParseException.class,
                () -> validation.verifyInput(new String[]{"bas??nana"}));
        Assertions.assertEquals("Input error: Bad input format.", exception.getMessage());

    }

    @Test
    public void testVerifyInputMultipleArgs() throws InputParseException {
        var output1 = validation.verifyInput(new String[]{"A","B","C"});
        Assertions.assertEquals("A,B,C", output1);

        var output2 = validation.verifyInput(new String[]{"F","P"});
        Assertions.assertEquals("F,P", output2);
    }


}
