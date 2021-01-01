package com.project.sap.controllers;

import com.project.sap.models.Client;
import com.project.sap.services.interfaces.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ClientsAddController {
    private String errorMessage = null;
    ClientService clientService;

    @Autowired
    public ClientsAddController(ClientService clientService) {
        this.clientService = clientService;
    }



    @GetMapping("/add-client")
    public ModelAndView getAddClient(){
        ModelAndView mav = new ModelAndView("add-client");
        mav.addObject("client",new Client());
        mav.addObject("error", errorMessage);
        return mav;
    }

    //TODO make the data save, and only the email dissapear
    @PostMapping("/add-client")
    public String addClient(Client client){
        try {
            clientService.addClient(client);
        } catch (Exception e) {
            errorMessage = "The email is already in use!";
            return "redirect:/add-client";
        }
        errorMessage = null;
        return "redirect:/home";
    }
}
