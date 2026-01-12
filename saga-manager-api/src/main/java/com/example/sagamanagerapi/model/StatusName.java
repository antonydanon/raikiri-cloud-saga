package com.example.sagamanagerapi.model;

public enum StatusName {
    COMPLETED,
    CANCELED;

    public static StatusName determineTransactionStatus(String status) {
        if (status.equals(COMPLETED.name())) {
            return COMPLETED;
        }
        return CANCELED;
    }
}
