package com.project.sap.services;

import com.project.sap.models.VideoCard;
import com.project.sap.repositories.VideoCardRepository;
import com.project.sap.services.interfaces.VideoCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VideoCardServiceImpl implements VideoCardService {

    VideoCardRepository videoCardRepository;
    @Autowired
    public VideoCardServiceImpl(VideoCardRepository videoCardRepository) {
        this.videoCardRepository = videoCardRepository;
    }

    public List<VideoCard> get() {
        return videoCardRepository.findAll();
    }

    public void add(VideoCard videoCard) {
        videoCard.setName(videoCard.getManufacturer() + " " + videoCard.getModel() + " " + videoCard.getMemory() + "GB " + videoCard.getTechnology());
        videoCardRepository.save(videoCard);
    }

    public void deleteById(long id) {
        videoCardRepository.deleteById(id);
    }

    public Optional<VideoCard> findById(long id) {
        return videoCardRepository.findById(id);
    }
}
