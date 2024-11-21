package com.example.tudoserazvan1101ticket2;

import java.io.Serializable;

public class Pacient implements Serializable {
    String patientName;
    float examinationCost;
    boolean insurance;

    public Pacient() {}

    public Pacient(String patientName, float examinationCost, boolean insurance) {
        this.patientName = patientName;
        this.examinationCost = examinationCost;
        this.insurance = insurance;
    }

    @Override
    public String toString() {
        return "Pacient{" +
                "patientName='" + patientName + '\'' +
                ", examinationCost=" + examinationCost +
                ", insurance=" + insurance +
                '}';
    }
}
