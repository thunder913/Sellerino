package com.project.sap.controllers;

import com.project.sap.models.*;
import com.project.sap.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AddComponentsController {

    //TODO make validaitons of values, so they are always numbers and set appropriate error messages
    private static final String addProductURLRedirect = "redirect:/add-product";
    private static String errorMessage = null;
    @Autowired
    private RAMService ramService;
    @Autowired
    private VideoCardService videoCardService;
    @Autowired
    private ScreenService screenService;
    @Autowired
    private StorageService storageService;
    @Autowired
    private ProcessorService processorService;

    @GetMapping("add-ram")
    public ModelAndView addRam(){
        ModelAndView mav = new ModelAndView("add-ram");
        mav.addObject("error", errorMessage);
        mav.addObject("ram", new RAM());
        return mav;
    }

    @PostMapping("add-ram")
    public String saveRam(@ModelAttribute RAM ram){
        ramService.add(ram);
        return "redirect:/add-product";
    }

    @GetMapping("add-processor")
    public ModelAndView addProcessor(){
        ModelAndView mav = new ModelAndView("add-processor");
        mav.addObject("error", errorMessage);
        mav.addObject("processor", new Processor());
        return mav;
    }

    @PostMapping("add-processor")
    public String saveProcessor(@ModelAttribute Processor processor){
        processorService.add(processor);
        return "redirect:/add-product";
    }

    @GetMapping("add-screen")
    public ModelAndView addScreen(){
        ModelAndView mav = new ModelAndView("add-screen");
        mav.addObject("error", errorMessage);
        mav.addObject("screen", new Screen());
        return mav;
    }

    @PostMapping("add-screen")
    public String saveScreen(@ModelAttribute Screen screen){
        screenService.add(screen);
        return "redirect:/add-product";
    }

    @GetMapping("add-storage")
    public ModelAndView addStorage(){
        ModelAndView mav = new ModelAndView("add-storage");
        mav.addObject("error", errorMessage);
        mav.addObject("storage", new Storage());
        return mav;
    }

    @PostMapping("add-storage")
    public String saveScreen(@ModelAttribute Storage storage){
        storageService.add(storage);
        return "redirect:/add-product";
    }

    @GetMapping("add-video-card")
    public ModelAndView addVideoCard(){
        ModelAndView mav = new ModelAndView("add-video-card");
        mav.addObject("error", errorMessage);
        mav.addObject("videoCard", new VideoCard());
        return mav;
    }

    @PostMapping("add-video-card")
    public String saveVideoCard(@ModelAttribute VideoCard videoCard){
        videoCardService.add(videoCard);
        return "redirect:/add-product";
    }

}
