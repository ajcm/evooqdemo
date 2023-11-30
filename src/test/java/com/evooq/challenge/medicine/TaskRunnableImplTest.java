package com.evooq.challenge.medicine;

import com.evooq.challenge.medicine.error.InputParseException;
import com.evooq.challenge.medicine.runner.TaskRunnableImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("prod") //force to use something else rather than test
public class TaskRunnableImplTest {

    @Autowired
    TaskRunnableImpl taskRunnable;

    @Test
    public void testEmptyInput() {
    }


    @Test
    public void testVerifyInput() {
        InputParseException exception;

        //empty String
        exception = Assertions.assertThrows(InputParseException.class,
                () -> taskRunnable.verifyInput(null));
        Assertions.assertEquals("Input error: Expecting one argument.", exception.getMessage());

        //empty String
        exception = Assertions.assertThrows(InputParseException.class,
                () -> taskRunnable.verifyInput(new String[]{""}));
        Assertions.assertEquals("Input error: argument is empty.", exception.getMessage());

        //multiple args
        exception = Assertions.assertThrows(InputParseException.class,
                () -> taskRunnable.verifyInput(new String[]{"banana", "apple"}));
        Assertions.assertEquals("Input error: Expecting only one argument.", exception.getMessage());

    }


}
