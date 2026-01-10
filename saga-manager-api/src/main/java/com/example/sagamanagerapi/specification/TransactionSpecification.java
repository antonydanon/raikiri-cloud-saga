package com.example.sagamanagerapi.specification;

import com.example.sagamanagerapi.model.StatusName;
import com.example.sagamanagerapi.model.Transaction;
import org.springframework.data.jpa.domain.Specification;

import java.sql.Timestamp;

import static com.example.sagamanagerapi.model.StatusName.*;

public class TransactionSpecification {

    public static Specification<Transaction> statusEqual(String status) {
        if (status.equals(ALL_STATUSES)) {
            return (root, query, cb) -> root.join("status").get("name").in(getAllStatuses());
        }
        return (root, query, cb) -> cb.equal(root.join("status").get("name"), StatusName.valueOf(status));
    }

    public static Specification<Transaction> executionDateBetween(Timestamp startDate, Timestamp endDate) {
        return (root, query, cb) -> cb.between(root.get("executionDate"), startDate, endDate);
    }
}
