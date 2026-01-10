package com.example.sagamanagerapi.starter;

import com.netflix.conductor.common.metadata.workflow.WorkflowDef;
import com.netflix.conductor.sdk.workflow.executor.WorkflowExecutor;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;
import java.util.List;

import static com.example.sagamanagerapi.utils.FileUtils.getContentFrom;

@Component
@RequiredArgsConstructor
public class ConductorWorkflowCreator implements ApplicationRunner {
    private static final List<String> WORKFLOW_PATHS = List.of(
            "/conductor/workflow/order_transaction_compensation.json",
            "/conductor/workflow/order_transaction.json"
    );

    private final ObjectMapper objectMapper;
    private final WorkflowExecutor executor;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        for (String workflowPath : WORKFLOW_PATHS) {
            String json = getContentFrom(workflowPath);
            WorkflowDef workflowDef = objectMapper.readValue(json, WorkflowDef.class);
            executor.registerWorkflow(workflowDef, true);
        }
    }
}
