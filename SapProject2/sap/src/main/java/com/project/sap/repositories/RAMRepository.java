package com.project.sap.repositories;

import com.project.sap.models.RAM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RAMRepository extends JpaRepository<RAM, Long> {
    @Override
    List<RAM> findAll();
}
