package com.project.sap.controllers;

import com.project.sap.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class UserDeleteController {

    UserService userService;

    @Autowired
    public UserDeleteController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/delete/users/{id}")
    public String deleteUser(@PathVariable(value = "id") String id, Model model, RedirectAttributes ra){
        long currentId = Long.parseLong(id);
        if (userService.hasSales(currentId)){
            ra.addFlashAttribute("error", "You cannot delete a user, who has sales!");
        }else {
            userService.deleteById(currentId);
        }return "redirect:/users";
    }
}
