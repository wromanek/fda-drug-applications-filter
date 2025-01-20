package com.romanek.drugs.fda.model;

import java.util.List;

public record Patient(
    String patientonsetage,
    String patientonsetageunit,
    String patientsex,
    String patientagegroup,
    String patientweight,
    PatientDeath patientdeath,
    List<Reaction> reaction,
    List<Drug> drug,
    Summary summary
) {
}
