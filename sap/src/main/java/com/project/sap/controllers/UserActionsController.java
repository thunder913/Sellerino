package com.project.sap.controllers;

import com.project.sap.models.User;
import com.project.sap.repositories.UserRepository;
import com.project.sap.services.UserService;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

//TODO Make possible for users to have laptops/sales and delete user from both places
@Controller
public class UserActionsController {

    @Autowired
    UserService userService;

    @GetMapping("/delete/users/{id}")
    public String deleteUser(@PathVariable(value = "id") String id){
        long currentId = Long.parseLong(id);
        userService.deleteById(currentId);
        return "redirect:/users";
    }
    //TODO make an actual error to show when trying to put a user email that already exists
    //TODO make it actually useful, because right now there are no sales or laptops per SR
    @GetMapping("/edit/users/{id}")
    public ModelAndView editUser(@PathVariable(value = "id") String id){
        ModelAndView mav = new ModelAndView("edit-user");
        long currentId = Long.parseLong(id);
        User user = userService.getUserById(currentId);
        mav.addObject("user", user);
        return mav;
    }

    @PostMapping("/edit/users/{id}")
    public String submitEdit(@PathVariable(value = "id") String id, User user){
        ModelAndView mav = new ModelAndView("");
        long currentId = Long.parseLong(id);
        User oldUser = userService.getUserById(currentId);
        user.setSales(oldUser.getSales());
        user.setPassword(oldUser.getPassword());
        userService.save(user);
        return "redirect:/users";
    }
}
