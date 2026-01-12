package com.example.sagamanagerapi.dto;

import com.example.sagamanagerapi.model.StatusName;
import java.sql.Timestamp;

public record TransactionDtoResponse(Timestamp executionDate, StatusName status) {
}
