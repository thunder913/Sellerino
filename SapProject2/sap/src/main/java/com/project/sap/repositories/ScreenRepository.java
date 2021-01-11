package com.project.sap.repositories;

import com.project.sap.models.Screen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScreenRepository extends JpaRepository<Screen, Long> {
    @Override
    List<Screen> findAll();
}
