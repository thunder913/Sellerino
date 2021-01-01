package com.project.sap.services;

import com.project.sap.models.*;
import com.project.sap.models.Dto.LaptopDto;
import com.project.sap.repositories.LaptopRepository;
import com.project.sap.services.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LaptopServiceImpl implements LaptopService {

    LaptopRepository laptopRepository;
    ProcessorService processorService;
    ScreenService screenService;
    VideoCardService videoCardService;
    RAMService ramService;
    StorageService storageService;

    @Autowired
    public LaptopServiceImpl(LaptopRepository laptopRepository, ProcessorService processorService, ScreenService screenService, VideoCardService videoCardService, RAMService ramService, StorageService storageService) {
        this.laptopRepository = laptopRepository;
        this.processorService = processorService;
        this.screenService = screenService;
        this.videoCardService = videoCardService;
        this.ramService = ramService;
        this.storageService = storageService;
    }

    public List<Laptop> get() {
        return laptopRepository.findAll();
    }

    public void add(Laptop item) {
        laptopRepository.save(item);
    }

    public void deleteById(long id) {
        laptopRepository.deleteById(id);
    }

    public Optional<Laptop> findById(long id) {
        return laptopRepository.findById(id);
    }
}
