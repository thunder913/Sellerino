package com.project.sap.controllers;

import com.project.sap.models.Laptop;
import com.project.sap.models.Sale;
import com.project.sap.models.User;
import com.project.sap.services.interfaces.LaptopService;
import com.project.sap.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class HomeController {

    UserService userService;
    LaptopService laptopService;

    @Autowired
    public HomeController(UserService userService, LaptopService laptopService) {
        this.userService = userService;
        this.laptopService = laptopService;
    }

    @GetMapping({"/", "/home"})
    public ModelAndView home(Model model){
        ModelAndView mav = new ModelAndView("home");
        Authentication auth = ((Authentication) SecurityContextHolder.getContext().getAuthentication());
        if (!auth.getPrincipal().equals("anonymousUser")) {
            User user = userService.getUserByEmail(auth.getName());
            mav.addObject("user", user.getFirstName() + " " + user.getLastName());
        }
        List<Laptop> laptopList = laptopService.get();
        if (laptopList.size()>=4){
            laptopList.sort((a,b)->compare(a,b));
            mav.addObject("laptops", laptopList.stream().limit(4).collect(Collectors.toList()));
            Laptop laptop = new Laptop();
            Set<Sale> sales = laptopList.get(0).getSales();
        }
        return mav;
    }


    private int compare(Laptop laptopOne, Laptop laptopTwo){
        double priceOne = laptopOne.getSales().stream().mapToDouble(x->x.getTotalPrice().doubleValue()).sum();
        double priceTwo = laptopTwo.getSales().stream().mapToDouble(x->x.getTotalPrice().doubleValue()).sum();
        return (int)Math.round(priceTwo-priceOne);
    }
}
