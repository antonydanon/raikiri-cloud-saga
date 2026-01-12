package com.example.sagamanagerapi.mapper;

import com.example.sagamanagerapi.dto.TransactionDtoResponse;
import com.example.sagamanagerapi.model.Transaction;

public class TransactionMapper {

    public static TransactionDtoResponse mapTransactionToTransactionDtoResponse(Transaction transaction) {
        return new TransactionDtoResponse(
                transaction.getExecutionDate(),
                transaction.getStatus().getName()
        );
    }
}
