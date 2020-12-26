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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

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

    //TODO make proper error messages to show when error is made, quantity == 0, laptop not selected!
    @PostMapping("/add-sale")
    public ModelAndView addSale(SaleDto saleDto){
        if (saleDto.getTotalPrice() == null || saleDto.getPriceForOne() == null){
            return new ModelAndView("redirect:/add-sale");
        }
        saleDto.setDate(Date.from((LocalDateTime.now()).toInstant(ZoneOffset.UTC)));
        Sale sale = salesService.convertDtoToSale(saleDto);
        salesService.save(sale);
        return new ModelAndView("/home");
    }


    @GetMapping("/delete/sales/{id}")
    public String editUser(@PathVariable(value = "id") String id){
        long currentId = Long.parseLong(id);
        salesService.deleteById(currentId);
        return "redirect:/sales-sr";
    }
}
