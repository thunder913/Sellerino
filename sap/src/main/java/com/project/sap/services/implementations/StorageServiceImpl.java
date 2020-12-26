package com.project.sap.services.implementations;

import com.project.sap.models.Storage;
import com.project.sap.repositories.StorageRepository;
import com.project.sap.services.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StorageServiceImpl implements StorageService{

    @Autowired
    StorageRepository storageRepository;

    public List<Storage> get() {
        return storageRepository.findAll();
    }

    public void add(Storage storage) {
        storage.setName(storage.getType() + " " + storage.getCapacity() + "GB " + storage.getConnector());
        storageRepository.save(storage);
    }

    public void deleteById(long id) {
        storageRepository.deleteById(id);
    }

    public Optional<Storage> findById(long id) {
        return storageRepository.findById(id);
    }
}
