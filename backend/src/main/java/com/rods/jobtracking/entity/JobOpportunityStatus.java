package com.rods.jobtracking.entity;

public enum JobOpportunityStatus {
    WISHLIST("wishlist"),
    APPLIED("applied"),
    LOST("lost"),
    IN_PROCESS("in_process"),
    CANCELED("canceled");

    private final String value;

    JobOpportunityStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
