package com.example.gymlog.user.enums;

public enum Role {

    USER("User"),
    ADMIN("Admin");

    private String displayName;

    Role(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
