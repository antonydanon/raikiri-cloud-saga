package com.example.sagamanagerapi.repository;

import com.example.sagamanagerapi.model.Status;
import com.example.sagamanagerapi.model.StatusName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StatusRepository extends JpaRepository<Status, Integer> {

    Optional<Status> findByName(StatusName name);
}
