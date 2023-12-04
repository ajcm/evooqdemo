package com.evooq.challenge.medicine.postprocessor;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;


/* This class will is forced upon tests, to avoid random results */
@Service
@Profile("test")
public class FlyingSpaghettiMonsterGeneratorDummy implements FlyingSpaghettiMonsterGenerator {

    /* 1 in a million change */
    public boolean isFlyingSpaghettiMonster() {
        return false;
    }


}
