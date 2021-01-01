package com.project.sap.services;

import com.project.sap.models.*;
import com.project.sap.services.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddComponentsServiceImpl implements AddComponentsService {

    @Autowired
    ProcessorService processorService;
    @Autowired
    RAMService ramService;
    @Autowired
    ScreenService screenService;
    @Autowired
    StorageService storageService;
    @Autowired
    VideoCardService videoCardService;

    @Override
    public List<RAM> getRams() {
        return ramService.get();
    }

    @Override
    public List<Screen> getScreens() {
        return screenService.get();
    }

    @Override
    public List<Processor> getProcessors() {
        return processorService.get();
    }

    @Override
    public List<VideoCard> getVideoCards() {
        return videoCardService.get();
    }

    @Override
    public List<Storage> getStorages() {
        return storageService.get();
    }
}
