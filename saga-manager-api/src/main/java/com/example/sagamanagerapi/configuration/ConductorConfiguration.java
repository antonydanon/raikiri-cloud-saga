package com.example.sagamanagerapi.configuration;

import com.netflix.conductor.sdk.workflow.executor.WorkflowExecutor;
import io.orkes.conductor.client.ApiClient;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConductorConfiguration {
    private static final int DEFAULT_POLLING_INTERVAL = 15;

    @Value("${conductor.api}")
    private String conductorApi;

    @Bean
    public ApiClient apiClient() {
        return new ApiClient(conductorApi);
    }

    @Bean(destroyMethod = "shutdown")
    public WorkflowExecutor workflowExecutor(ApiClient apiClient) {
        return new WorkflowExecutor(apiClient, DEFAULT_POLLING_INTERVAL);
    }

    @Bean
    public Faker dataFaker() {
        return new Faker();
    }
}
