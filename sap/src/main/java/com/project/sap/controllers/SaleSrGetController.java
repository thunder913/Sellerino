package com.project.sap.controllers;

import com.project.sap.models.User;
import com.project.sap.services.interfaces.SalesService;
import com.project.sap.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SaleSrGetController {
    SalesService salesService;
    UserService userService;

    @Autowired
    public SaleSrGetController(SalesService salesService, UserService userService) {
        this.salesService = salesService;
        this.userService = userService;
    }

    @GetMapping("/sales-sr")
    public ModelAndView getSalesSr(){
        User user = this.getUser();
        ModelAndView mav = new ModelAndView("sales-sr");
        mav.addObject("sales", user.getSales());
        return mav;
    }


    private User getUser(){
        String username = ((Authentication) SecurityContextHolder.getContext().getAuthentication()).getName();
        User user = userService.getUserByEmail(username);
        return user;
    }
}
