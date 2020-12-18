package com.example.microservice.libraryservice.issue.model;

public enum IssueStatus {
    ISSUED("I"),
    RETURNED("R");

    IssueStatus(String status) {
        this.code = status;
    }
    private String code;

    public String code() {
        return code;
    }
}

