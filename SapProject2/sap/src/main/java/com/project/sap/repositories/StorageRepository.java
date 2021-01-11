package com.project.sap.repositories;

import com.project.sap.models.Storage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StorageRepository extends JpaRepository<Storage, Long> {
    @Override
    List<Storage> findAll();
}
