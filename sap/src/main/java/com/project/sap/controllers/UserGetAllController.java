package com.project.sap.controllers;

import com.project.sap.models.User;
import com.project.sap.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class UserGetAllController {
    UserService userService;

    @Autowired
    public UserGetAllController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/users")
    public ModelAndView getSr(Model model){
        List<User> users = userService.get();
        ModelAndView mav = new ModelAndView("users");
        mav.addObject("users", users);
        return mav;
    }
}
