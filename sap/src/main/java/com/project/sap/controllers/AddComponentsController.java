package com.project.sap.controllers;

import com.project.sap.models.*;
import com.project.sap.models.Dto.*;
import com.project.sap.services.interfaces.*;
import com.project.sap.utils.*;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AddComponentsController {

    private static final String addProductURLRedirect = "redirect:/add-product";
    private RAMService ramService;
    private VideoCardService videoCardService;
    private ScreenService screenService;
    private StorageService storageService;
    private ProcessorService processorService;

    @Autowired
    public AddComponentsController(RAMService ramService, VideoCardService videoCardService, ScreenService screenService, StorageService storageService, ProcessorService processorService) {
        this.ramService = ramService;
        this.videoCardService = videoCardService;
        this.screenService = screenService;
        this.storageService = storageService;
        this.processorService = processorService;
    }

    @GetMapping("/add-ram")
    public ModelAndView addRam(Model model){
        ModelAndView mav = new ModelAndView("add-ram");
        mav.addObject("ram", new RAM());
        return mav;
    }

    @PostMapping("/add-ram")
    public ModelAndView saveRam(@ModelAttribute RamDto ramDto, Model model){
        InputValidation.numberCheck("Memory", ramDto.getMemory(), model);
        InputValidation.lenghtCheck("Manufacturer", ramDto.getManufacturer(), model);
        if (model.getAttribute("error") != null){
            return this.addRam(model);
        }
        RAM ram = RamMapper.INSTANCE.ramDtoToRam(ramDto);
        ramService.add(ram);
        return new ModelAndView("redirect:/add-product");
    }

    @GetMapping("/add-processor")
    public ModelAndView addProcessor(Model model){
        ModelAndView mav = new ModelAndView("add-processor");
        mav.addObject("processor", new Processor());
        return mav;
    }

    @PostMapping("/add-processor")
    public ModelAndView saveProcessor(@ModelAttribute ProcessorDto processorDto, Model model){
        InputValidation.numberCheck("Cores value", processorDto.getCores(), model);
        InputValidation.numberCheck("Speed value", processorDto.getSpeed(), model);
        InputValidation.lenghtCheck("Manufacturer", processorDto.getManufacturer(), model);
        InputValidation.lenghtCheck("Model", processorDto.getModel(), model);
        if (model.getAttribute("error") != null){
            return this.addProcessor(model);
        }
        Processor processor = ProcessorMapper.INSTANCE.processorDtoToProcessor(processorDto);
        processorService.add(processor);
        return new ModelAndView("redirect:/add-product");
    }

    @GetMapping("/add-screen")
    public ModelAndView addScreen(Model model){
        ModelAndView mav = new ModelAndView("add-screen");
        mav.addObject("screen", new Screen());
        return mav;
    }

    @PostMapping("/add-screen")
    public ModelAndView saveScreen(@ModelAttribute ScreenDto screenDto, Model model){
        InputValidation.numberCheck("Diagonal", screenDto.getDiagonal(), model);
        InputValidation.numberCheck("Response Time", screenDto.getResponseTime(), model);
        InputValidation.numberCheck("Refresh Rate", screenDto.getRefreshRate(), model);
        if (model.getAttribute("error") != null){
            return this.addScreen(model);
        }
        Screen screen = ScreenMapper.INSTANCE.screenDtoToScreen(screenDto);
        screenService.add(screen);
        return new ModelAndView("redirect:/add-product");
    }

    @GetMapping("/add-storage")
    public ModelAndView addStorage(Model model){
        ModelAndView mav = new ModelAndView("add-storage");
        mav.addObject("storage", new Storage());
        return mav;
    }

    @PostMapping("/add-storage")
    public ModelAndView saveScreen(@ModelAttribute StorageDto storageDto, Model model){
        InputValidation.numberCheck("Capacity", storageDto.getCapacity(), model);
        InputValidation.lenghtCheck("Connector", storageDto.getConnector(), model);
        InputValidation.lenghtCheck("Connection type", storageDto.getType(), model);
        if (model.getAttribute("error") != null){
            return this.addStorage(model);
        }
        Storage storage = StorageMapper.INSTANCE.storageDtoToStorage(storageDto);
        storageService.add(storage);
        return new ModelAndView("redirect:/add-product");
    }

    @GetMapping("/add-video-card")
    public ModelAndView addVideoCard(Model model){
        ModelAndView mav = new ModelAndView("add-video-card");
        mav.addObject("videoCard", new VideoCard());
        return mav;
    }

    @PostMapping("/add-video-card")
    public ModelAndView saveVideoCard(@ModelAttribute VideoCardDto videoCardDto, Model model){
        InputValidation.numberCheck("Memory", videoCardDto.getMemory(), model);
        InputValidation.lenghtCheck("Manufacturer", videoCardDto.getManufacturer(), model);
        InputValidation.lenghtCheck("Model", videoCardDto.getModel(), model);
        InputValidation.lenghtCheck("Technology", videoCardDto.getTechnology(), model);
        if (model.getAttribute("error") != null){
            return this.addVideoCard(model);
        }
        VideoCard videoCard = VideoCardMapper.INSTANCE.videoCardDtoToVideoCard(videoCardDto);
        videoCardService.add(videoCard);
        return new ModelAndView("redirect:/add-product");
    }
}
