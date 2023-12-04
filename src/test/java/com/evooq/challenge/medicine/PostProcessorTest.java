package com.evooq.challenge.medicine;

import com.evooq.challenge.medicine.error.ProcessingException;
import com.evooq.challenge.medicine.model.ClinicInput;
import com.evooq.challenge.medicine.model.Medicine;
import com.evooq.challenge.medicine.postprocessor.FlyingSpaghettiMonsterGenerator;
import com.evooq.challenge.medicine.postprocessor.PostProcessor;
import com.evooq.challenge.medicine.services.ClinicApplicationImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;

import java.util.List;

import static com.evooq.challenge.medicine.model.Patient.*;

/**
 * Note:
 * test FlyingSpaghettiMonster forcing condition true, mind in other tests this conditions if force to false
 */
@SpringBootTest
@Configuration
public class PostProcessorTest {

    @Test
    public void testSpaghetti() throws ProcessingException {
        var postProcessor = new PostProcessor(new FlyingSpaghettiMonsterGeneratorTest());
        var clinicApplication = new ClinicApplicationImpl(postProcessor);

        //Dead is resurrected
        var clinicStatus1 = clinicApplication.process(ClinicInput.of(List.of(F, H, D, T, X), List.of(Medicine.I)));
        Assertions.assertEquals("1-2-1-1-0", clinicStatus1.toString());

        //One will be saved
        var clinicStatus2 = clinicApplication.process(ClinicInput.of(List.of(X, X), List.of(Medicine.I)));
        Assertions.assertEquals("0-1-0-0-1", clinicStatus2.toString());

        // D dont die
        var clinicStatus3 = clinicApplication.process(ClinicInput.of(List.of(D, D), List.of(Medicine.I)));
        Assertions.assertEquals("0-0-2-0-0", clinicStatus3.toString());

        // One D will resurrect
        var clinicStatus4 = clinicApplication.process(ClinicInput.of(List.of(D, D)));
        Assertions.assertEquals("0-1-0-0-1", clinicStatus4.toString());
    }

    private static class FlyingSpaghettiMonsterGeneratorTest implements FlyingSpaghettiMonsterGenerator {

        @Override
        public boolean isFlyingSpaghettiMonster() {
            return true;
        }
    }
}
