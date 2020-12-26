package com.project.sap.services.implementations;

import com.project.sap.models.*;
import com.project.sap.models.Dto.LaptopDto;
import com.project.sap.repositories.LaptopRepository;
import com.project.sap.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LaptopServiceImpl implements LaptopService{

    @Autowired
    LaptopRepository laptopRepository;
    @Autowired
    ProcessorService processorService;
    @Autowired
    ScreenService screenService;
    @Autowired
    VideoCardService videoCardService;
    @Autowired
    RAMService ramService;
    @Autowired
    StorageService storageService;

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

    public Laptop mapDtoToLaptop(LaptopDto laptopDto){
        Processor processor = processorService.findById(laptopDto.getProcessorId()).get();
        Screen screen = screenService.findById(laptopDto.getScreenId()).get();
        VideoCard videoCard = videoCardService.findById(laptopDto.getVideoCardId()).get();
        Set<RAM> rams = new HashSet<>();
        for (long id:laptopDto.getRamIds()) {
            rams.add(ramService.findById(id).get());
        }

        Set<Storage> storageList = new HashSet<>();
        for (long id:laptopDto.getStorageIds()) {
            storageList.add(storageService.findById(id).get());
        }

        Set<Image> images = new HashSet<>();
        for (String imgUrl: laptopDto.getImageUrls()) {
            images.add(new Image(imgUrl));
        }

        Laptop laptop = new Laptop();
        laptop.setManufacturer(laptopDto.getManufacturer());
        laptop.setModel(laptopDto.getModel());
        laptop.setPrice(laptopDto.getPrice());
        laptop.setQuantity(laptopDto.getQuantity());
        laptop.setProcessor(processor);
        laptop.setScreen(screen);
        laptop.setVideoCard(videoCard);
        laptop.setRam(rams);
        laptop.setStorage(storageList);
        laptop.setImages(images);
        return laptop;
    }

    @Override
    public LaptopDto mapLaptopToDto(Laptop laptop) {
        LaptopDto laptopDto = new LaptopDto();
        laptopDto.setId(laptop.getId());
        laptopDto.setManufacturer(laptop.getManufacturer());
        laptopDto.setModel(laptop.getModel());
        laptopDto.setPrice(laptop.getPrice());
        laptopDto.setQuantity(laptop.getQuantity());
        laptopDto.setProcessorId(laptop.getProcessor().getId());
        laptopDto.setScreenId(laptop.getScreen().getId());
        laptopDto.setVideoCardId(laptop.getVideoCard().getId());

        List<Long> ramIds = new ArrayList<>();
        for (RAM ram: laptop.getRam()) {
            ramIds.add(ram.getId());
        }
        List<Long> storageIds = new ArrayList<>();
        for (Storage storage: laptop.getStorage()) {
            storageIds.add(storage.getId());
        }
        laptopDto.setRamIds(ramIds);
        laptopDto.setStorageIds(storageIds);
        List<String> imageUrls = new ArrayList<>();
        for (Image image: laptop.getImages()) {
            imageUrls.add(image.getUrl());
        }
        laptopDto.setImageUrls(imageUrls);
        return laptopDto;
    }
}
