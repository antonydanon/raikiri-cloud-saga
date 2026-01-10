package com.example.sagamanagerapi.service;

import com.example.sagamanagerapi.dto.TransactionResponse;
import com.example.sagamanagerapi.model.StatusName;
import com.example.sagamanagerapi.model.Transaction;
import com.example.sagamanagerapi.repository.TransactionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import java.sql.Timestamp;

import static com.example.sagamanagerapi.specification.TransactionSpecification.executionDateBetween;
import static com.example.sagamanagerapi.specification.TransactionSpecification.statusEqual;
import static org.springframework.data.jpa.domain.Specification.where;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final StatusService statusService;
    private final TransactionRepository transactionRepository;

    public Page<TransactionResponse> getAllBySearchAndPage(
            String status, String startDate,
            String endDate, int countPerPage, int pageNumber) {
        return transactionRepository
                .findAll(
                        where(
                                statusEqual(status)
                                        .and(
                                                executionDateBetween(
                                                        Timestamp.valueOf(startDate),
                                                        Timestamp.valueOf(endDate)
                                                )
                                        )
                        ), PageRequest.of(pageNumber, countPerPage))
                .map(this::convert);
    }

    public Transaction createNewAndSave(String txId, Timestamp executionDate, StatusName status) {
        Transaction transaction = new Transaction();
        transaction.setTxId(txId);
        transaction.setExecutionDate(executionDate);
        transaction.setStatus(statusService.getStatusByName(status));
        return transactionRepository.save(transaction);
    }

    private TransactionResponse convert(Transaction transaction) {
        return new TransactionResponse(
                transaction.getExecutionDate(),
                transaction.getStatus().getName()
        );
    }
}

