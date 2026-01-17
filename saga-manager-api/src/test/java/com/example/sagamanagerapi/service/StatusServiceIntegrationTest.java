package com.example.sagamanagerapi.service;

import com.example.sagamanagerapi.exception.StatusNotFoundException;
import com.example.sagamanagerapi.model.Status;
import com.example.sagamanagerapi.model.StatusName;
import com.example.sagamanagerapi.repository.StatusRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@Testcontainers
class StatusServiceIntegrationTest {

    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15")
            .withDatabaseName("raikiri_test")
            .withUsername("test")
            .withPassword("test");

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
        registry.add("spring.jpa.hibernate.ddl-auto", () -> "create-drop");
    }

    @Autowired
    private StatusService statusService;

    @Autowired
    private StatusRepository statusRepository;

    @Test
    void getStatusByName_Found() {
        StatusName testName = StatusName.COMPLETED;
        Status status = new Status();
        status.setName(testName);
        statusRepository.saveAndFlush(status);

        Status result = statusService.getStatusByName(testName);

        assertThat(result.getName()).isEqualTo(testName);
        assertThat(result.getId()).isGreaterThan(0L);
    }

    @Test
    void getStatusByName_NotFound() {
        assertThatThrownBy(() -> statusService.getStatusByName(StatusName.CANCELED))
                .isInstanceOf(StatusNotFoundException.class);
    }
}
