package com.example.sagamanagerapi.controller;

import com.example.sagamanagerapi.dto.TransactionResponse;
import com.example.sagamanagerapi.service.TransactionService;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/transactions")
public class TransactionController {
    private final TransactionService transactionService;

    @GetMapping
    public ResponseEntity<Page<TransactionResponse>> getAll(
            @RequestParam @Pattern(
                    regexp = "COMPLETED|CANCELED|ALL",
                    message = "Status must be COMPLETED, CANCELED or ALL."
            )
            String status,
            @RequestParam @Pattern(
                    regexp = "^\\d{4}-(?:0[1-9]|1[0-2])-(?:0[1-9]|[12]\\d|3[01]) (?:[01]\\d|2[0-3]):[0-5]\\d:[0-5]\\d$",
                    message = "Start date format must be yyyy-mm-dd hh:mm:ss."
            )
            String startDate,
            @RequestParam @Pattern(
                    regexp = "^\\d{4}-(?:0[1-9]|1[0-2])-(?:0[1-9]|[12]\\d|3[01]) (?:[01]\\d|2[0-3]):[0-5]\\d:[0-5]\\d$",
                    message = "End date format must be yyyy-mm-dd hh:mm:ss."
            )
            String endDate,
            @RequestParam @Min(value = 1, message = "Page size must not be less than one.")
            int countPerPage,
            @RequestParam @Min(value = 0, message = "Page index must not be less than zero.")
            int pageNumber) {
        Page<TransactionResponse> page = transactionService.getAllBySearchAndPage(
                status, startDate, endDate,
                countPerPage, pageNumber
        );
        return ResponseEntity.ok().body(page);
    }
}