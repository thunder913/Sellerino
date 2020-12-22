package com.project.sap.api;

import com.project.sap.models.*;
import com.project.sap.repositories.UserRepository;
import org.hibernate.validator.internal.constraintvalidators.hv.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

@Controller
public class RegisterController {

    private String errorMessage = null;

    @Autowired
    private UserRepository userRepo;

    //TODO make the model take the errorMessage variable, without the need of a private String errorMessage
    //and give the GET /register the values for email and names, so not to write em again
    @GetMapping("/register")
    public String register(WebRequest request, Model model) {
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("error", errorMessage);
        return "register";
    }

    @PostMapping("/do-register")
    public String postRegister(User user){
        if (!isValidEmail(user.getEmail())){
            errorMessage = "Invalid Email!";
            return "redirect:/register";
        }
        else if (!user.getRepeatPassword().equals(user.getPassword())){
            errorMessage = "Passwords must match!";
            return "redirect:/register";
        }else if (user.getRole().equals("Invalid")){
            errorMessage="You must choose a role!";
            return "redirect:/register";
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRepeatPassword(passwordEncoder.encode(user.getRepeatPassword()));
        UserDto userToAdd = (UserDto) user;
        try {
            userRepo.save(userToAdd);
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