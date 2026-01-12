package com.example.sagamanagerapi.service;

import com.netflix.conductor.common.run.Workflow;
import com.netflix.conductor.sdk.workflow.executor.WorkflowExecutor;
import lombok.RequiredArgsConstructor;
import net.datafaker.Faker;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

import static com.example.sagamanagerapi.model.StatusName.determineTransactionStatus;

@Service
@RequiredArgsConstructor
public class TransactionExecutorService {
    private static final String TRANSACTION_NAME = "order_transaction";
    private static final int DEFAULT_TRANSACTION_VERSION = 1;

    private final WorkflowExecutor executor;
    private final TransactionService transactionService;
    private final Faker dataFaker;

    @Scheduled(fixedRate = 15000, initialDelay = 50000)
    public void launchOrderTransaction() throws ExecutionException, InterruptedException {
        String txId = UUID.randomUUID().toString();

        Workflow workflow = executor.executeWorkflow(
                TRANSACTION_NAME, DEFAULT_TRANSACTION_VERSION,
                prepareStubParameters(txId)
        ).get();
        transactionService.save(
                txId, new Timestamp(workflow.getStartTime()),
                determineTransactionStatus(workflow.getStatus().name())
        );
    }

    private Map<String, Object> prepareStubParameters(String txId) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("txId", txId);
        parameters.put("amount", BigDecimal.valueOf(
                dataFaker.number().randomDouble(2, 10, 5000)
        ));
        parameters.put("address", dataFaker.address().fullAddress());
        parameters.put("message", dataFaker.lorem().sentence(3, 5));
        return parameters;
    }
}
