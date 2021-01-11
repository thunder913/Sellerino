package com.project.sap.services.interfaces;

import com.project.sap.models.VideoCard;

import java.util.List;
import java.util.Optional;

public interface VideoCardService {
    List<VideoCard> get();

    void add(VideoCard videoCard);

    void deleteById(long id);

    Optional<VideoCard> findById(long id);
}
