package com.project.sap.controllers;

import com.project.sap.services.interfaces.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ClientsDeleteController {

    ClientService clientService;

    @Autowired
    public ClientsDeleteController(ClientService clientService) {
        this.clientService = clientService;
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
}
