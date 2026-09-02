package com.example.gymlog.user.enums;

public enum PrimaryWorkoutGoal {

    HYPERTROPHY_AND_STRENGTH("Hypertrophy + strength"),
    PURE_STRENGTH("Pure Strength"),
    FAT_LOSS("Fat loss"),
    CONDITIONING("Conditioning");

    private String displayName;

    PrimaryWorkoutGoal(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return this.displayName;
    }

}
