package com.evooq.challenge.medicine;

import com.evooq.challenge.medicine.error.InputParseException;
import com.evooq.challenge.medicine.error.ProcessingException;
import com.evooq.challenge.medicine.input.Transform;
import com.evooq.challenge.medicine.model.ClinicInput;
import com.evooq.challenge.medicine.model.ClinicStatus;
import com.evooq.challenge.medicine.model.Medicine;
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

    @Autowired
    private Transform transform;

    @Test
    public void testClinicStatus() throws ProcessingException {
        ClinicStatus clinicStatus = new ClinicStatus(1, 2, 3, 4, 5);
        // F,H,D,T,X
        Assertions.assertEquals("1-2-3-4-5", clinicStatus.toString());
    }


    @Test
    public void testClinicInput() throws ProcessingException {
        // F,H,D,T,X
        var clinicStatus = clinicApplication.process(new ClinicInput());
        Assertions.assertEquals("0-0-0-0-0", clinicStatus.toString());

        var clinicStatus1 = clinicApplication.process(ClinicInput.of(List.of(H, H, H, X, X, T)));
        Assertions.assertEquals("0-3-0-1-2", clinicStatus1.toString());

        //D without I -> X
        var clinicStatus2 = clinicApplication.process(ClinicInput.of(List.of(F, H, D, T, X)));
        Assertions.assertEquals("1-1-0-1-2", clinicStatus2.toString());

        //D without I -> X
        var clinicStatus2a = clinicApplication.process(ClinicInput.of(List.of(F, H, D, T, X), List.of(Medicine.I)));
        Assertions.assertEquals("1-1-1-1-1", clinicStatus2a.toString());

        var clinicStatus3 = clinicApplication.process(ClinicInput.of(List.of(F, H, D, T, X, F, H, D, T, X)));
        Assertions.assertEquals("2-2-0-2-4", clinicStatus3.toString());

        var clinicStatus4 = clinicApplication.process(ClinicInput.of(List.of(H)));
        Assertions.assertEquals("0-1-0-0-0", clinicStatus4.toString());

        var clinicStatus5 = clinicApplication.process(ClinicInput.of(List.of(), List.of()));
        Assertions.assertEquals("0-0-0-0-0", clinicStatus5.toString());

        var clinicStatus6 = clinicApplication.process(ClinicInput.of(List.of(), List.of()));
        Assertions.assertEquals("0-0-0-0-0", clinicStatus6.toString());

        var clinicStatus7 = clinicApplication.process(ClinicInput.of(List.of(), List.of(Medicine.I)));
        Assertions.assertEquals("0-0-0-0-0", clinicStatus7.toString());

    }

    @Test
    public void testExamples() throws ProcessingException, InputParseException {
        // F,H,D,T,X

        var inputData = transform.getClinicInput("D,D");
        var clinicStatus = clinicApplication.process(inputData);
        Assertions.assertEquals("0-0-0-0-2", clinicStatus.toString());

        var inputData2 = transform.getClinicInput("F,P");
        var clinicStatus2 = clinicApplication.process(inputData2);
        Assertions.assertEquals("0-1-0-0-0", clinicStatus2.toString());

        var inputData3 = transform.getClinicInput("T,F,D,An,I");
        var clinicStatus3 = clinicApplication.process(inputData3);
        Assertions.assertEquals("2-0-1-0-0", clinicStatus3.toString());
    }

    @Test
    public void testAlgorithm() throws ProcessingException, InputParseException {
        // F,H,D,T,X

        // H + I,An -> F
        var inputData = transform.getClinicInput("H,H,H,An,I");
        var clinicStatus = clinicApplication.process(inputData);
        Assertions.assertEquals("3-0-0-0-0", clinicStatus.toString());

        var inputData1a = transform.getClinicInput("H,H,H,F,F,X,An,I,P");
        var clinicStatus1a = clinicApplication.process(inputData1a);
        Assertions.assertEquals("5-0-0-0-1", clinicStatus1a.toString());

        var inputData1b = transform.getClinicInput("T,H,H,H,F,F,X,An,I,P");
        var clinicStatus1b = clinicApplication.process(inputData1b);
        Assertions.assertEquals("6-0-0-0-1", clinicStatus1b.toString());


        //P + As -> X
        var inputData2 = transform.getClinicInput("H,H,H,X,An,I,P,As");
        var clinicStatus2 = clinicApplication.process(inputData2);
        Assertions.assertEquals("0-0-0-0-4", clinicStatus2.toString());

        var inputData2a = transform.getClinicInput("H,H,H,X,T,T,F,F,F,D,D,An,I,P");
        var clinicStatus2a = clinicApplication.process(inputData2a);
        Assertions.assertEquals("8-0-2-0-1", clinicStatus2a.toString());

        //D -> X
        var inputData3 = transform.getClinicInput("D,D,D");
        var clinicStatus3 = clinicApplication.process(inputData3);
        Assertions.assertEquals("0-0-0-0-3", clinicStatus3.toString());

        //nothing changes
        var inputData4 = transform.getClinicInput("D,D,D,As,P");
        var clinicStatus4 = clinicApplication.process(inputData3);
        Assertions.assertEquals("0-0-0-0-3", clinicStatus3.toString());

    }
}
