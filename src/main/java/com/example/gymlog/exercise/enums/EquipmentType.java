package com.example.gymlog.exercise.enums;

public enum EquipmentType {


    BARBELL("Barbell"),
    DUMBBELS("Dumbels"),
    MACHINE("Machine"),
    CABLE("Cable"),
    BODYWEIGHT("Bodyweight"),
    KETTLEBELL("Kettlebell"),
    RESISTANCE_BAND("Resistance Band");




    private String displayName;

    EquipmentType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
