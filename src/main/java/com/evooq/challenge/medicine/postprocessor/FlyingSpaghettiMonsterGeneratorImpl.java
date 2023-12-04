package com.evooq.challenge.medicine.postprocessor;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@Profile("!test")
public class FlyingSpaghettiMonsterGeneratorImpl implements FlyingSpaghettiMonsterGenerator {

    /* 1 in a million change */
    @Override
    public boolean isFlyingSpaghettiMonster() {
        return new Random().nextInt(1000000) == 0;
    }
}
