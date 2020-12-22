package com.project.sap.repositories;

import com.project.sap.models.VideoCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoCardRepository extends JpaRepository<VideoCard, Long> {
    @Override
    List<VideoCard> findAll();
}
