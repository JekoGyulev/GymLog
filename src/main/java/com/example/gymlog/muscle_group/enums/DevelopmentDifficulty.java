package com.example.gymlog.muscle_group.enums;

public enum DevelopmentDifficulty {

    EASY("Easy"),
    MEDIUM("Medium"),
    HARD("Hard");


    private String displayName;

    DevelopmentDifficulty(String displayName) {
        this.displayName = displayName;
    }


    public String getDisplayName() {
        return displayName;
    }
}
