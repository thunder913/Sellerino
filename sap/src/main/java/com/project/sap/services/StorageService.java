package com.project.sap.services;

import com.project.sap.models.Storage;

import java.util.List;
import java.util.Optional;

public interface StorageService{
    List<Storage> get();

    void add(Storage storage);

    void deleteById(long id);

    Optional<Storage> findById(long id);
}
