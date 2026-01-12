package com.example.restaurantapi.dto;

import java.math.BigDecimal;

public record OrderSaveDtoRequest(String txId, BigDecimal amount) {
}
