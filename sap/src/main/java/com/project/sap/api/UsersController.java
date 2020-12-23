package com.project.sap.api;

import com.project.sap.models.UserDto;
import com.project.sap.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
//TODO Make buttons look nice and the checkbox too
@Controller
public class UsersController {
    @Autowired
    UserRepository userRepository;

    @GetMapping(value = "users")
    public ModelAndView getSr(){
        List<UserDto> users = userRepository.findAll();
        ModelAndView mav = new ModelAndView("users");
        mav.addObject("users", users);
        return mav;
    }
}
