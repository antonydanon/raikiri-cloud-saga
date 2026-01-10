package com.example.sagamanagerapi.model;

import java.util.List;

public enum StatusName {
    COMPLETED,
    CANCELED;

    public static final String ALL_STATUSES = "ALL";

    public static StatusName determineTransactionStatus(String status) {
        if (status.equals(COMPLETED.name())) {
            return COMPLETED;
        }
        return CANCELED;
    }

    public static List<StatusName> getAllStatuses() {
        return List.of(values());
    }
}
