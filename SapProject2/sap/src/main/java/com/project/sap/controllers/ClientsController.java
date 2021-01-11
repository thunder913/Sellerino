package com.project.sap.controllers;

import com.project.sap.models.Client;
import com.project.sap.services.interfaces.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ClientsController {
    private String errorMessage = null;
    ClientService clientService;

    @Autowired
    public ClientsController(ClientService clientService) {
        this.clientService = clientService;
    }



    @GetMapping("/add-client")
    public ModelAndView getAddClient(){
        ModelAndView mav = new ModelAndView("add-client");
        mav.addObject("client",new Client());
        mav.addObject("error", errorMessage);
        return mav;
    }

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

    @GetMapping("/delete/clients/{id}")
    public String deleteUser(@PathVariable(value = "id") String id, RedirectAttributes ra){
        long currentId = Long.parseLong(id);
        if (clientService.hasSales(currentId)){
            ra.addFlashAttribute("error", "You cannot delete a client, who has sale!");
        }else {
            clientService.deleteById(currentId);
        }
        return "redirect:/clients";
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

    @GetMapping("/clients")
    public ModelAndView getClients(){
        ModelAndView mav = new ModelAndView("clients");
        mav.addObject("clients", clientService.get());
        return mav;
    }
}
