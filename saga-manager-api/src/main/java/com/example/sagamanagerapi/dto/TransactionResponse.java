package com.example.sagamanagerapi.dto;

import com.example.sagamanagerapi.model.StatusName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionResponse {
    private Timestamp executionDate;
    private StatusName status;
}
