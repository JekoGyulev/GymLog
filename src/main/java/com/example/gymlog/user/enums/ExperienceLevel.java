package com.example.gymlog.user.enums;

public enum ExperienceLevel {

    BEGINNER("Beginner"),
    INTERMEDIATE("Intermediate"),
    ADVANCED("Advanced"),
    COMPETITIVE_ATHLETE("Competitive Athelete");

    private String displayName;

    ExperienceLevel(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return this.displayName;
    }
}
