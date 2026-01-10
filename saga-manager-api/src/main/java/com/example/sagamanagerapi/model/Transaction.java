package com.example.sagamanagerapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String txId;
    private Timestamp executionDate;
    @ManyToOne
    @JoinColumn(name="status_id")
    private Status status;
}
