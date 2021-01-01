package com.project.sap.controllers;

import com.project.sap.models.Dto.SaleDto;
import com.project.sap.models.Sale;
import com.project.sap.models.User;
import com.project.sap.services.interfaces.ClientService;
import com.project.sap.services.interfaces.LaptopService;
import com.project.sap.services.interfaces.SalesService;
import com.project.sap.services.interfaces.UserService;
import com.project.sap.utils.SalesMapperResolver;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Controller
public class SalesEditController {
    UserService userService;
    LaptopService laptopService;
    ClientService clientService;
    SalesService salesService;
    SalesMapperResolver salesMapper;
    @Autowired
    public SalesEditController(UserService userService, LaptopService laptopService, ClientService clientService, SalesService salesService, SalesMapperResolver salesMapperResolver) {
        this.userService = userService;
        this.laptopService = laptopService;
        this.clientService = clientService;
        this.salesService = salesService;
        this.salesMapper = salesMapperResolver;
    }

    @GetMapping("/edit/sales/{id}")
    public ModelAndView editSale(@PathVariable(value = "id") String id){
        long currentId = Long.parseLong(id);
        Sale sale = salesService.getById(currentId);
        ModelAndView mav = new ModelAndView("edit-sale");
        mav.addObject("laptops", laptopService.get());
        mav.addObject("clients", clientService.get());
        mav.addObject("sale", salesMapper.saleToSaleDto(sale));
        return mav;
    }

    @PostMapping("/edit/sales/{id}")
    public String submitEditSale(@PathVariable(value="id") String id, SaleDto saleDto, RedirectAttributes ra){
        long currentId = Long.parseLong(id);
        if (!NumberUtils.isCreatable(saleDto.getQuantity()) || saleDto.getTotalPrice() == null || saleDto.getPriceForOne() == null || Integer.parseInt(saleDto.getQuantity())<=0){
            ra.addFlashAttribute("error", "The quantity and the price cannot be negative or empty!");
            return "redirect:/edit/sales/" + currentId;
        }
        saleDto.setDate(Date.from((LocalDateTime.now()).toInstant(ZoneOffset.UTC)));
        Sale sale = salesMapper.saleDtoToSale(saleDto);
        sale.setId(currentId);
        sale.setSeller(salesService.getById(currentId).getSeller());
        salesService.save(sale);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getAuthorities().stream().anyMatch(x->x.getAuthority().equals("Admin"))){
            return "redirect:/sales-admin";
        }else if(auth.getAuthorities().stream().anyMatch(x->x.getAuthority().equals("SR"))){
            return "redirect:/sales-sr";
        }
        return "/home";
    }

}
