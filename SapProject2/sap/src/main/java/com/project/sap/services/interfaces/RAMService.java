package com.project.sap.services.interfaces;

import com.project.sap.models.RAM;

import java.util.List;
import java.util.Optional;

public interface RAMService {
    List<RAM> get();

    void add(RAM ram);

    void deleteById(long id);

    Optional<RAM> findById(long id);
}
