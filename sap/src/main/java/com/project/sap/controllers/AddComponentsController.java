package com.project.sap.controllers;

import com.project.sap.models.*;
import com.project.sap.models.Dto.RamDto;
import com.project.sap.services.interfaces.*;
import com.project.sap.utils.RamMapper;
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

    //TODO make validaitons of values, so they are always numbers and set appropriate error messages
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
        numberCheck("Memory", ramDto.getMemory(), model);
        if (model.getAttribute("error") != null){
            return this.addRam(model);
        }
        RAM ram = RamMapper.INSTANCE.ramDtoToRam(ramDto);
        ramService.add(ram);
        return new ModelAndView("redirect:/add-product");
    }

    @GetMapping("/add-processor")
    public ModelAndView addProcessor(){
        ModelAndView mav = new ModelAndView("add-processor");
        mav.addObject("processor", new Processor());
        return mav;
    }

    @PostMapping("/add-processor")
    public String saveProcessor(@ModelAttribute Processor processor){
        processorService.add(processor);
        return "redirect:/add-product";
    }

    @GetMapping("/add-screen")
    public ModelAndView addScreen(){
        ModelAndView mav = new ModelAndView("add-screen");
        mav.addObject("screen", new Screen());
        return mav;
    }

    @PostMapping("/add-screen")
    public String saveScreen(@ModelAttribute Screen screen){
        screenService.add(screen);
        return "redirect:/add-product";
    }

    @GetMapping("/add-storage")
    public ModelAndView addStorage(){
        ModelAndView mav = new ModelAndView("add-storage");
        mav.addObject("storage", new Storage());
        return mav;
    }

    @PostMapping("/add-storage")
    public String saveScreen(@ModelAttribute Storage storage){
        storageService.add(storage);
        return "redirect:/add-product";
    }

    @GetMapping("/add-video-card")
    public ModelAndView addVideoCard(){
        ModelAndView mav = new ModelAndView("add-video-card");
        mav.addObject("videoCard", new VideoCard());
        return mav;
    }

    @PostMapping("/add-video-card")
    public String saveVideoCard(@ModelAttribute VideoCard videoCard){
        videoCardService.add(videoCard);
        return "redirect:/add-product";
    }

    private Model numberCheck(String name,String number, Model model){
        if(!NumberUtils.isCreatable(number)){
            model.addAttribute("error", name + " must be a number!");
        }
        return model;
    }
}
