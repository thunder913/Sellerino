package com.project.sap.api;

import com.project.sap.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

//TODO Make possible for users to have laptops/sales and delete user from both places
//TODO Make configurations in the security so that it requires authentication
@Controller
public class UserActionsController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/delete/users/{id}")
    public String deleteUser(@PathVariable(value = "id") String id){
        long currentId = Long.parseLong(id);
        userRepository.deleteById(currentId);
        return "redirect:/users";
    }

    //TODO make it actually useful, because right now there are no sales or laptops per SR
    @GetMapping("/edit/users/{id}")
    public String editUser(@PathVariable(value = "id") String id){
        long currentId = Long.parseLong(id);
        userRepository.findById(currentId);
        return "redirect:/users";
    }
}
