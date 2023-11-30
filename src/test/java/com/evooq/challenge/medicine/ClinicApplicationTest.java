package com.evooq.challenge.medicine;

import com.evooq.challenge.medicine.error.ProcessingException;
import com.evooq.challenge.medicine.model.ClinicInput;
import com.evooq.challenge.medicine.model.ClinicStatus;
import com.evooq.challenge.medicine.services.ClinicApplication;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static com.evooq.challenge.medicine.model.Patient.*;

@SpringBootTest
public class ClinicApplicationTest {

    @Autowired
    private ClinicApplication clinicApplication;

    @Test
    public void testClinicStatus() throws ProcessingException {
        ClinicStatus clinicStatus = new ClinicStatus(1, 2, 3, 4, 5);
        Assertions.assertEquals("1-2-3-4-5", clinicStatus.toString());
    }


    @Test
    public void testClinicInput() throws ProcessingException {
        var clinicStatus = clinicApplication.process(new ClinicInput());
        Assertions.assertEquals("0-0-0-0-0", clinicStatus.toString());

        var clinicStatus1 = clinicApplication.process(ClinicInput.of(List.of(H, H, H, X, X, T)));
        Assertions.assertEquals("0-3-0-1-2", clinicStatus1.toString());

        var clinicStatus2 = clinicApplication.process(ClinicInput.of(List.of(F, H, D, T, X)));
        Assertions.assertEquals("1-1-1-1-1", clinicStatus2.toString());

        var clinicStatus3 = clinicApplication.process(ClinicInput.of(List.of(F, H, D, T, X, F, H, D, T, X)));
        Assertions.assertEquals("2-2-2-2-2", clinicStatus3.toString());

        var clinicStatus4 = clinicApplication.process(ClinicInput.of(List.of(H)));
        Assertions.assertEquals("0-1-0-0-0", clinicStatus4.toString());

    }
}
