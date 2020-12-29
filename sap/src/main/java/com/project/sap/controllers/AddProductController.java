package com.project.sap.controllers;

import com.project.sap.models.*;
import com.project.sap.models.Dto.LaptopDto;
import com.project.sap.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AddProductController {

    @Autowired
    private LaptopService laptopService;
    @Autowired
    AddComponentsService addComponentsService;

    @GetMapping("add-product")
    public ModelAndView addProduct(){
        ModelAndView mav = new ModelAndView("addproduct");
        mav.addObject("ramsList", addComponentsService.getRams());
        mav.addObject("processorList", addComponentsService.getProcessors());
        mav.addObject("screenList", addComponentsService.getScreens());
        mav.addObject("storageList", addComponentsService.getStorages());
        mav.addObject("videoCardList", addComponentsService.getVideoCards());
        mav.addObject("laptop", new LaptopDto());
        return mav;
    }

    @PostMapping("add-product")
    public String saveProduct(LaptopDto laptopDto){
        Laptop laptop = laptopService.mapDtoToLaptop(laptopDto);
        laptopService.add(laptop);
        return "redirect:/home";
    }
}
