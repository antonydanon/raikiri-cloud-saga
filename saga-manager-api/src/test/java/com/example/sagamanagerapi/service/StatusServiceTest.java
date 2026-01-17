package com.example.sagamanagerapi.service;

import com.example.sagamanagerapi.exception.StatusNotFoundException;
import com.example.sagamanagerapi.model.Status;
import com.example.sagamanagerapi.model.StatusName;
import com.example.sagamanagerapi.repository.StatusRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StatusServiceTest {

    @Mock
    private StatusRepository statusRepository;

    @InjectMocks
    private StatusService statusService;

    @Test
    void getStatusByName_returnsStatus_whenFound() {
        StatusName name = StatusName.COMPLETED;
        Status expectedStatus = new Status();
        expectedStatus.setName(name);
        when(statusRepository.findByName(name)).thenReturn(Optional.of(expectedStatus));

        Status result = statusService.getStatusByName(name);

        assertEquals(expectedStatus, result);
        verify(statusRepository).findByName(name);
        verifyNoMoreInteractions(statusRepository);
    }

    @Test
    void getStatusByName_throwsException_whenNotFound() {
        StatusName name = StatusName.COMPLETED;
        when(statusRepository.findByName(name)).thenReturn(Optional.empty());

        StatusNotFoundException exception = assertThrows(
                StatusNotFoundException.class,
                () -> statusService.getStatusByName(name)
        );

        assertEquals("Status with name " + name + " was not found.", exception.getMessage());
        verify(statusRepository).findByName(name);
    }
}