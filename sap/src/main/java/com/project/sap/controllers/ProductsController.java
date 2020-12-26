package com.project.sap.controllers;

import com.project.sap.models.Laptop;
import com.project.sap.models.Dto.LaptopDto;
import com.project.sap.services.AddComponentsService;
import com.project.sap.services.LaptopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

//TODO Make pages, so that not all info is loaded at once
@Controller
public class ProductsController {

    @Autowired
    AddComponentsService addComponentsService;
    @Autowired
    LaptopService laptopService;

    @GetMapping("/products")
    public ModelAndView getProducts(){
        ModelAndView mav = new ModelAndView("products");

        List<Laptop> laptopList = laptopService.get();

        mav.addObject("products", laptopList);
        return mav;

    }

    @GetMapping("/delete/products/{id}")
    public String deleteUser(@PathVariable(value = "id") String id){
        long currentId = Long.parseLong(id);
        laptopService.deleteById(currentId);
        return "redirect:/products";
    }

    //TODO make edit product page work fine
    //TODO Fix image ids
    @GetMapping("edit/products/{id}")
    public ModelAndView editProduct(@PathVariable(value ="id") String id){
        long currentId = Long.parseLong(id);
        Laptop laptop = laptopService.findById(currentId).get();
        ModelAndView mav = new ModelAndView("edit-product");
        LaptopDto laptopDto = laptopService.mapLaptopToDto(laptop);
        //String ramIds = "[" + laptopDto.getRamIds().stream().map(String::valueOf).collect(Collectors.joining(",")) + "]";
        //String storageIds = "[" + laptopDto.getStorageIds().stream().map(String::valueOf).collect(Collectors.joining(",")) + "]";
        mav.addObject("imageList", laptop.getImages());
        mav.addObject("product", laptopDto);
        mav.addObject("ramsList", addComponentsService.getRams());
        mav.addObject("processorList", addComponentsService.getProcessors());
        mav.addObject("screenList", addComponentsService.getScreens());
        mav.addObject("storageList", addComponentsService.getStorages());
        mav.addObject("videoCardList", addComponentsService.getVideoCards());

        return mav;
    }
}
