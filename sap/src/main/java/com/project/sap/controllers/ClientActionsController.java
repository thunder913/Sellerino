package com.project.sap.controllers;

import com.project.sap.models.Client;
import com.project.sap.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ClientActionsController {
    private String errorMessage = null;
    @Autowired
    ClientService clientService;

    @GetMapping("/clients")
    public ModelAndView getClients(){
        ModelAndView mav = new ModelAndView("clients");
        mav.addObject("clients", clientService.get());
        return mav;
    }


    @GetMapping("/delete/clients/{id}")
    public String deleteUser(@PathVariable(value = "id") String id){
        long currentId = Long.parseLong(id);
        clientService.deleteById(currentId);
        return "redirect:/clients";
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
