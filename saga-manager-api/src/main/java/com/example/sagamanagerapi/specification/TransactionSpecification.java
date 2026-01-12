package com.example.sagamanagerapi.specification;

import com.example.sagamanagerapi.model.Transaction;
import org.springframework.data.jpa.domain.Specification;

import java.sql.Timestamp;
import java.util.List;

public class TransactionSpecification {

    public static Specification<Transaction> statusIn(List<String> statuses) {
        return (root, query, cb) -> root.join("status").get("name").in(statuses);
    }

    public static Specification<Transaction> executionDateBetween(Timestamp startDate, Timestamp endDate) {
        return (root, query, cb) -> cb.between(root.get("executionDate"), startDate, endDate);
    }
}
