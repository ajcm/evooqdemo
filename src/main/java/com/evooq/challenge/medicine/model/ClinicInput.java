package com.evooq.challenge.medicine.model;

import java.util.LinkedHashSet;
import java.util.List;

public class ClinicInput {

    //order does not matter
    List<ClinicStatus> patients;

    //must keep order and unique values
    LinkedHashSet<Medicine> medicines;

}
