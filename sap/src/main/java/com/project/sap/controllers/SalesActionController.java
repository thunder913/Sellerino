package com.project.sap.controllers;

import com.project.sap.models.Dto.SaleDto;
import com.project.sap.models.Sale;
import com.project.sap.services.AddComponentsService;
import com.project.sap.services.ClientService;
import com.project.sap.services.LaptopService;
import com.project.sap.services.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SalesActionController {

    @Autowired
    LaptopService laptopService;
    @Autowired
    ClientService clientService;
    @Autowired
    SalesService salesService;
    @GetMapping("/sales-sr")
    public ModelAndView getSalesSr(){
        ModelAndView mav = new ModelAndView("sales-sr");
        mav.addObject("sales", salesService.getAll());
        return mav;
    }

    @GetMapping("/add-sale")
    public ModelAndView addSale(){
        ModelAndView mav = new ModelAndView("add-sale");
        mav.addObject("sales", salesService.getAll());
        mav.addObject("laptops", laptopService.get());
        mav.addObject("clients", clientService.get());
        mav.addObject("sale", new SaleDto());
        return mav;
    }

}
