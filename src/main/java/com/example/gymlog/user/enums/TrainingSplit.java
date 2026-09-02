package com.example.gymlog.user.enums;

public enum TrainingSplit {

    PUSH_PULL_LEGS("Push / Pull / Legs"),
    UPPER_LOWER("Upper / Lower"),
    FULL_BODY("Full body"),
    BRO_SPLIT("Bro split");

    private String displayName;

    TrainingSplit(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return this.displayName;
    }
}
