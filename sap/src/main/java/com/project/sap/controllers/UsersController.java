package com.project.sap.controllers;

import com.project.sap.models.User;
import com.project.sap.repositories.UserRepository;
import com.project.sap.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
@Controller
public class UsersController {
    @Autowired
    UserService userService;

    @GetMapping(value = "users")
    public ModelAndView getSr(){
        List<User> users = userService.get();
        ModelAndView mav = new ModelAndView("users");
        mav.addObject("users", users);
        return mav;
    }
}
