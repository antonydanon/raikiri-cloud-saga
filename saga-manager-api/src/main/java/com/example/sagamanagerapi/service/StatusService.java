package com.example.sagamanagerapi.service;

import com.example.sagamanagerapi.exception.StatusNotFoundException;
import com.example.sagamanagerapi.model.Status;
import com.example.sagamanagerapi.model.StatusName;
import com.example.sagamanagerapi.repository.StatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StatusService {
    private final StatusRepository statusRepository;

    public Status getStatusByName(StatusName name) {
        return statusRepository.findByName(name)
                .orElseThrow(() -> new StatusNotFoundException("Status with name " + name + " was not found."));
    }
}
