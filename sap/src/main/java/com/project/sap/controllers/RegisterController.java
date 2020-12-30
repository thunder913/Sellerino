package com.project.sap.controllers;

import com.project.sap.models.*;
import com.project.sap.models.Dto.UserDto;
import com.project.sap.services.interfaces.UserService;
import com.project.sap.utils.UserMapper;
import org.hibernate.validator.internal.constraintvalidators.hv.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

@Controller
public class RegisterController {

    private String errorMessage = null;

    private UserService userService;

    @Autowired
    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    //TODO make the model take the errorMessage variable, without the need of a private String errorMessage
    //and give the GET /register the values for email and names, so not to write em again
    @GetMapping("/register")
    public String register(WebRequest request, Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        model.addAttribute("error", errorMessage);
        return "register";
    }

    @PostMapping("/do-register")
    public String postRegister(UserDto userDto){
        if (!isValidEmail(userDto.getEmail())){
            errorMessage = "Invalid Email!";
            return "redirect:/register";
        }
        else if (!userDto.getRepeatPassword().equals(userDto.getPassword())){
            errorMessage = "Passwords must match!";
            return "redirect:/register";
        }else if (userDto.getRole().equals("Invalid")){
            errorMessage="You must choose a role!";
            return "redirect:/register";
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userDto.setRepeatPassword(passwordEncoder.encode(userDto.getRepeatPassword()));
        User userToAdd = UserMapper.INSTANCE.userDtoToUser(userDto);
        try {
            userService.save(userToAdd);
        }catch (DataIntegrityViolationException ex){
            errorMessage = "Email already exists!";
            return "redirect:/register";
        }
        errorMessage = null;
        return "login";
    }

    private boolean isValidEmail(String email) {
        EmailValidator emailValidator = new EmailValidator();
        return emailValidator.isValid(email, null);
    }
}