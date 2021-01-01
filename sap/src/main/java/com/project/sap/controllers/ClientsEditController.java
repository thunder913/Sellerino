package com.project.sap.controllers;

import com.project.sap.models.Client;
import com.project.sap.services.interfaces.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ClientsEditController {

    ClientService clientService;

    @Autowired
    public ClientsEditController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/edit/clients/{id}")
    public ModelAndView editClient(@PathVariable(value = "id") String id){
        long currentId = Long.parseLong(id);
        Client client = clientService.getById(currentId).get();
        ModelAndView mav = new ModelAndView("edit-client");
        mav.addObject(client);
        return mav;
    }

    @PostMapping("/edit/client/{id}")
    public String submitEditClient(@PathVariable(value = "id") String id, Client client){
        long currentId = Long.parseLong(id);
        Client oldCLient = clientService.getById(currentId).get();
        client.setSales(oldCLient.getSales());
        clientService.addClient(client);
        return "redirect:/clients";
    }

}
