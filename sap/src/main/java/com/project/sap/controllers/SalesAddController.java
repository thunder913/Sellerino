package com.project.sap.controllers;

import com.project.sap.models.Dto.SaleDto;
import com.project.sap.models.Sale;
import com.project.sap.models.User;
import com.project.sap.services.interfaces.ClientService;
import com.project.sap.services.interfaces.LaptopService;
import com.project.sap.services.interfaces.SalesService;
import com.project.sap.services.interfaces.UserService;
import com.project.sap.utils.SalesMapperResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Controller
public class SalesAddController {

    LaptopService laptopService;
    SalesService salesService;
    ClientService clientService;
    UserService userService;
    SalesMapperResolver salesMapperResolver;
    @Autowired
    public SalesAddController(LaptopService laptopService, SalesService salesService, ClientService clientService, UserService userService, SalesMapperResolver salesMapperResolver) {
        this.laptopService = laptopService;
        this.salesService = salesService;
        this.clientService = clientService;
        this.userService = userService;
        this.salesMapperResolver = salesMapperResolver;
    }

    @GetMapping("/add-sale")
    public ModelAndView addSale(Model model){
        ModelAndView mav = new ModelAndView("add-sale");
        mav.addObject("sales", salesService.getAll());
        mav.addObject("laptops", laptopService.get());
        mav.addObject("clients", clientService.get());
        mav.addObject("sale", new SaleDto());
        return mav;
    }

    @PostMapping("/add-sale")
    public ModelAndView addSale(SaleDto saleDto, Model model){
        if (saleDto.getTotalPrice() == null || saleDto.getPriceForOne() == null || Integer.parseInt(saleDto.getQuantity())==0){
            model.addAttribute("error", "The prices and quantities cannot be empty or zero!");
            return this.addSale(model);
        }
        saleDto.setDate(Date.from((LocalDateTime.now()).toInstant(ZoneOffset.UTC)));
        Sale sale = salesMapperResolver.saleDtoToSale(saleDto);
        User seller = this.getUser();
        sale.setSeller(seller);
        salesService.save(sale);
        return new ModelAndView("/home");
    }


    private User getUser(){
        String username = ((Authentication) SecurityContextHolder.getContext().getAuthentication()).getName();
        User user = userService.getUserByEmail(username);
        return user;
    }
}
