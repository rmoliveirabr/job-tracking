package com.rods.jobtracking.entity;

public enum JobApplicationStatus {
    WISHLIST("WISHLIST"),
    APPLIED("APPLIED"),
    LOST("LOST"),
    IN_PROCESS("IN_PROCESS"),
    CANCELED("CANCELED");

    private final String value;

    JobApplicationStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
