package com.project.sap.controllers;

import com.project.sap.services.interfaces.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ClientsGetController {

    ClientService clientService;

    @Autowired
    public ClientsGetController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/clients")
    public ModelAndView getClients(){
        ModelAndView mav = new ModelAndView("clients");
        mav.addObject("clients", clientService.get());
        return mav;
    }
}
