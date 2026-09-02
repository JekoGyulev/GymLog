package com.example.gymlog.user.enums;

public enum Unit {

    METRIC("Metric (kg·cm)"),
    IMPERIAL("Imperial (lb·in)");

    private String displayName;

    Unit(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return this.displayName;
    }

}
