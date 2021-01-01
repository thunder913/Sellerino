package com.project.sap.controllers;

import com.project.sap.models.Laptop;
import com.project.sap.services.interfaces.LaptopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ProductsGetController {

    LaptopService laptopService;

    @Autowired
    public ProductsGetController(LaptopService laptopService) {
        this.laptopService = laptopService;
    }

    @GetMapping("/products")
    public ModelAndView getProducts(){
        ModelAndView mav = new ModelAndView("products");

        List<Laptop> laptopList = laptopService.get();

        mav.addObject("products", laptopList);
        return mav;

    }
}
