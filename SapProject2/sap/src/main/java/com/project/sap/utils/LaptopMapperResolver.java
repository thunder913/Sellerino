package com.project.sap.utils;

import com.project.sap.models.*;
import com.project.sap.models.Dto.LaptopDto;
import com.project.sap.services.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.Generated;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class LaptopMapperResolver{

    private static final String addProductURLRedirect = "redirect:/add-product";
    private LaptopService laptopService;
    private RAMService ramService;
    private VideoCardService videoCardService;
    private ScreenService screenService;
    private StorageService storageService;
    private ProcessorService processorService;

    @Autowired
    public LaptopMapperResolver(RAMService ramService, VideoCardService videoCardService, ScreenService screenService, StorageService storageService, ProcessorService processorService, LaptopService laptopService) {
        this.ramService = ramService;
        this.videoCardService = videoCardService;
        this.screenService = screenService;
        this.storageService = storageService;
        this.processorService = processorService;
        this.laptopService = laptopService;
    }

    public Laptop laptopDtoToLaptop(LaptopDto laptopDto) {
        if ( laptopDto == null ) {
            return null;
        }

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
        laptop.setPrice(Float.parseFloat(laptopDto.getPrice()));
        laptop.setProcessor(processor);
        laptop.setScreen(screen);
        laptop.setVideoCard(videoCard);
        laptop.setRam(rams);
        laptop.setStorage(storageList);
        laptop.setImages(images);
        return laptop;
    }

    public LaptopDto laptopToLaptopDto(Laptop laptop) {
        if ( laptop == null ) {
            return null;
        }

        LaptopDto laptopDto = new LaptopDto();
        laptopDto.setId(laptop.getId());
        laptopDto.setManufacturer(laptop.getManufacturer());
        laptopDto.setModel(laptop.getModel());
        laptopDto.setPrice(String.valueOf(laptop.getPrice()));
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
