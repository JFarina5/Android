package com.example.jfarina.easypill.model.and.db.directory;

public class MedModel {
    private String medName, notes, dosage, initialAmount;
    private int numDays, frequency, medicationId;

    public void setMedicationID(int medicationID){this.medicationId = medicationID;}

    public void setNumDays(int numDays){this.numDays = numDays;}

    public void setFrequency(int frequency){this.frequency = frequency;}

    public int getMedicationId(){return medicationId;}

    @Override
    public String toString() {
        return "MedModel{" +
                "medName='" + medName + '\'' +
                ", frequency='" + frequency + '\'' +
                ", notes='" + notes + '\'' +
                ", dosage='" + dosage + '\'' +
                ", initialAmount='" + initialAmount + '\'' +
                ", numDays=" + numDays +
                '}';
    }

    public MedModel(){

    }

    public MedModel(String medName, String notes, String dosage, String initialAmount,
                    int frequency, int numDays) {
        this.medName = medName;
        this.notes = notes;
        this.dosage = dosage;
        this.initialAmount = initialAmount;
        this.frequency = frequency;
        this.numDays = numDays;

    }

    public void setMedName(String medName) {
        this.medName = medName;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public void setInitialAmount(String initialAmount) {
        this.initialAmount = initialAmount;
    }

    public String getMedName() {
        return medName;
    }

    public String getNotes() {
        return notes;
    }

    public String getDosage() {
        return dosage;
    }

    public String getInitialAmount() {
        return initialAmount;
    }

    public int getFrequency() {
        return frequency;
    }

    public int getNumDays() {
        return numDays;
    }
}
