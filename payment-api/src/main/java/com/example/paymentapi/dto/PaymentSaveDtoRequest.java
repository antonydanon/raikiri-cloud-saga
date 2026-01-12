package com.example.paymentapi.dto;

import java.math.BigDecimal;

public record PaymentSaveDtoRequest(String txId, BigDecimal amount) {
}
