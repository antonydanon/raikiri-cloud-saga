package com.example.sagamanagerapi.controller;

import com.example.sagamanagerapi.dto.TransactionDtoResponse;
import com.example.sagamanagerapi.mapper.TransactionMapper;
import com.example.sagamanagerapi.model.Transaction;
import com.example.sagamanagerapi.service.TransactionService;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.example.sagamanagerapi.utils.ValidationUtils.DATE_PATTERN;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/transactions")
public class TransactionController {
    private final TransactionService transactionService;

    @GetMapping
    public Page<TransactionDtoResponse> getAll(
            @RequestParam List<String> statuses,
            @RequestParam @Pattern(
                    regexp = DATE_PATTERN,
                    message = "Start date format must be yyyy-mm-dd hh:mm:ss."
            )
            String startDate,
            @RequestParam @Pattern(
                    regexp = DATE_PATTERN,
                    message = "End date format must be yyyy-mm-dd hh:mm:ss."
            )
            String endDate,
            @RequestParam @Min(value = 1, message = "Page size must not be less than one.")
            int countPerPage,
            @RequestParam @Min(value = 0, message = "Page index must not be less than zero.")
            int pageNumber) {
        Page<Transaction> page = transactionService.getAllBySearchAndPage(
                statuses, startDate, endDate,
                countPerPage, pageNumber
        );
        return page.map(TransactionMapper::mapTransactionToTransactionDtoResponse);
    }
}