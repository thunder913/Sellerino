package com.project.sap.controllers;

import com.project.sap.models.*;
import com.project.sap.models.Dto.LaptopDto;
import com.project.sap.services.interfaces.AddComponentsService;
import com.project.sap.services.interfaces.LaptopService;
import com.project.sap.utils.InputValidation;
import com.project.sap.utils.LaptopMapperResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProductsAddController {

    private LaptopService laptopService;
    AddComponentsService addComponentsService;
    LaptopMapperResolver laptopMapper;

    @Autowired
    public ProductsAddController(LaptopService laptopService, AddComponentsService addComponentsService, LaptopMapperResolver laptopMapper) {
        this.laptopService = laptopService;
        this.addComponentsService = addComponentsService;
        this.laptopMapper = laptopMapper;
    }

    @GetMapping("/add-product")
    public ModelAndView addProduct(Model model){
        ModelAndView mav = new ModelAndView("addproduct");
        mav.addObject("ramsList", addComponentsService.getRams());
        mav.addObject("processorList", addComponentsService.getProcessors());
        mav.addObject("screenList", addComponentsService.getScreens());
        mav.addObject("storageList", addComponentsService.getStorages());
        mav.addObject("videoCardList", addComponentsService.getVideoCards());
        mav.addObject("laptop", new LaptopDto());
        return mav;
    }

    @PostMapping("/add-product")
    public ModelAndView saveProduct(LaptopDto laptopDto, Model model){
        InputValidation.lenghtCheck("Manufacturer",laptopDto.getManufacturer(), model);
        InputValidation.lenghtCheck("Model", laptopDto.getModel(), model);
        InputValidation.numberCheck("Price", laptopDto.getPrice(), model);
        if (model.getAttribute("error") != null){
            return this.addProduct(model);
        }
        Laptop laptop = laptopMapper.laptopDtoToLaptop(laptopDto);
        laptopService.add(laptop);
        return new ModelAndView("redirect:/home");
    }
}
