package com.project.sap.services;

import com.project.sap.models.Processor;

import java.util.List;
import java.util.Optional;

public interface ProcessorService {
    List<Processor> get();

    void add(Processor processor);

    void deleteById(long id);

    Optional<Processor> findById(long id);
}
