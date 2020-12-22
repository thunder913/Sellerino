package com.project.sap.api;

import com.project.sap.models.*;
import com.project.sap.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class DBController{

    @Autowired
    RAMRepository RAMRepository;
    @Autowired
    StorageRepository storageRepository;
    @Autowired
    ScreenRepository screenRepository;
    @Autowired
    VideoCardRepository videoCardRepository;
    @Autowired
    ProcessorRepository processorRepository;
    @Autowired
    LaptopRepository laptopRepository;

    public List<RAM> getRams(){
        return RAMRepository.findAll();
    }
    public List<Storage> getStorages(){
        return storageRepository.findAll();
    }
    public List<Screen> getScreens(){
        return screenRepository.findAll();
    }
    public List<VideoCard> getVideoCards(){
        return videoCardRepository.findAll();
    }
    public List<Processor> getProcessors(){
        return processorRepository.findAll();
    }
    public List<Laptop> getLaptops(){
        return laptopRepository.findAll();
    }

    public void addRam(RAM ram){
        ram.setName(ram.getManufacturer() + " - " + ram.getMemory() + "GB");
        RAMRepository.save(ram);
    }

    public void addLaptop(Laptop laptop){
        laptopRepository.save(laptop);
    }

    public void addProcessor(Processor processor){
        processor.setName(processor.getManufacturer() + " " + processor.getModel() + " " + processor.getCores() + " " + processor.getSpeed() + "GHz");
        processorRepository.save(processor);
    }

    public void addStorage(Storage storage){
        storage.setName(storage.getType() + " " + storage.getCapacity() + "GB " + storage.getConnector());
        storageRepository.save(storage);
    }

    public void addScreen(Screen screen){
        screen.setName(screen.getDiagonal() + " inches " + screen.getRefreshRate() + "Hz " + screen.getResponseTime() + "ms response time.");
        screenRepository.save(screen);
    }

    public void addVideoCard(VideoCard videoCard){
        videoCard.setName(videoCard.getManufacturer() + " " + videoCard.getModel() + " " + videoCard.getMemory() + "GB " + videoCard.getTechnology());
        videoCardRepository.save(videoCard);
    }

    public Optional<RAM> findRamById(long id){
        return RAMRepository.findById(id);
    }

    public Optional<Storage> findStorageById(long id){
        return storageRepository.findById(id);
    }

    public Optional<Screen> findScreenById(long id){
        return screenRepository.findById(id);
    }

    public Optional<Processor> findProcessorById(long id){
        return processorRepository.findById(id);
    }

    public Optional<VideoCard> findVideoCardById(long id){
        return videoCardRepository.findById(id);
    }
}
