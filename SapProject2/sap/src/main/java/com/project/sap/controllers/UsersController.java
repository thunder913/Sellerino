package com.project.sap.controllers;

import com.project.sap.models.User;
import com.project.sap.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class UsersController {

    UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/edit/users/{id}")
    public ModelAndView editUser(@PathVariable(value = "id") String id){
        ModelAndView mav = new ModelAndView("edit-user");
        long currentId = Long.parseLong(id);
        User user = userService.getUserById(currentId);
        mav.addObject("user", user);
        return mav;
    }

    @PostMapping("/edit/users/{id}")
    public String submitEdit(@PathVariable(value = "id") String id, User user, RedirectAttributes ra){
        long currentId = Long.parseLong(id);
        User oldUser = userService.getUserById(currentId);
        if (!oldUser.getEmail().equals(user.getEmail())){
            if(userService.getUserByEmail(user.getEmail())!= null){
                ra.addFlashAttribute("error", "There is already a user with this email!");
                return "redirect:/edit/users/" + currentId;
            }
        }
        user.setSales(oldUser.getSales());
        user.setPassword(oldUser.getPassword());
        userService.save(user);
        return "redirect:/users";
    }

    @GetMapping(value = "/users")
    public ModelAndView getSr(Model model){
        List<User> users = userService.get();
        ModelAndView mav = new ModelAndView("users");
        mav.addObject("users", users);
        return mav;
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
