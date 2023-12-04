package com.evooq.challenge.medicine.postprocessor;

import com.evooq.challenge.medicine.model.ClinicStatus;
import com.evooq.challenge.medicine.model.Patient;
import org.springframework.stereotype.Service;

@Service
public class PostProcessor {

    private final FlyingSpaghettiMonsterGenerator flyingSpaghettiMonsterGenerator;

    public PostProcessor(FlyingSpaghettiMonsterGenerator flyingSpaghettiMonsterGenerator) {
        this.flyingSpaghettiMonsterGenerator = flyingSpaghettiMonsterGenerator;
    }

    public ClinicStatus process(ClinicStatus clinicStatus) {
        if (flyingSpaghettiMonsterGenerator.isFlyingSpaghettiMonster()) {
            //Save one
            if (clinicStatus.get(Patient.X) > 0) {
                clinicStatus.inc(Patient.X, -1);
                clinicStatus.inc(Patient.H, 1);
            }
        }
        return clinicStatus;
    }
}
