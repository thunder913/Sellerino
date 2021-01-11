package com.project.sap.repositories;

import com.project.sap.models.Processor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProcessorRepository extends JpaRepository<Processor, Long>{
    @Override
    List<Processor> findAll();
}