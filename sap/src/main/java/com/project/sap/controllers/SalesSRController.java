package com.project.sap.controllers;

import com.project.sap.models.Dto.SaleDto;
import com.project.sap.models.Sale;
import com.project.sap.models.User;
import com.project.sap.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Controller
public class SalesSRController {

    @Autowired
    UserService userService;
    @Autowired
    LaptopService laptopService;
    @Autowired
    ClientService clientService;
    @Autowired
    SalesService salesService;
    @GetMapping("/sales-sr")
    public ModelAndView getSalesSr(){
        User user = this.getUser();
        ModelAndView mav = new ModelAndView("sales-sr");
        mav.addObject("sales", user.getSales());
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
        if (saleDto.getTotalPrice() == null || saleDto.getPriceForOne() == null || saleDto.getQuantity()==0){
            return new ModelAndView("redirect:/add-sale");
        }
        saleDto.setDate(Date.from((LocalDateTime.now()).toInstant(ZoneOffset.UTC)));
        Sale sale = salesService.convertDtoToSale(saleDto);
        User seller = this.getUser();
        sale.setSeller(seller);
        salesService.save(sale);
        return new ModelAndView("/home");
    }


    @GetMapping("/delete/sales/{id}")
    public String editUser(@PathVariable(value = "id") String id){
        long currentId = Long.parseLong(id);
        salesService.deleteById(currentId);
        return "redirect:/home";
    }

    @GetMapping("/edit/sales/{id}")
    public ModelAndView editSale(@PathVariable(value = "id") String id){
        long currentId = Long.parseLong(id);
        Sale sale = salesService.getById(currentId);
        ModelAndView mav = new ModelAndView("edit-sale");
        mav.addObject("laptops", laptopService.get());
        mav.addObject("clients", clientService.get());
        mav.addObject("sale", salesService.convertSaleToDto(sale));
        return mav;
    }

    @PostMapping("/edit/sales/{id}")
    public String submitEditSale(@PathVariable(value="id") String id, SaleDto saleDto){
        long currentId = Long.parseLong(id);
        if (saleDto.getTotalPrice() == null || saleDto.getPriceForOne() == null || saleDto.getQuantity()==0){
            return "redirect:/edit/sales/" + currentId;
        }
        saleDto.setDate(Date.from((LocalDateTime.now()).toInstant(ZoneOffset.UTC)));
        Sale sale = salesService.convertDtoToSale(saleDto);
        sale.setId(currentId);
        User seller = this.getUser();
        sale.setSeller(seller);
        salesService.save(sale);
        return "redirect:/home";
    }

    private User getUser(){
        String username = ((Authentication)SecurityContextHolder.getContext().getAuthentication()).getName();
        User user = userService.getUserByEmail(username);
        return user;
    }
}
