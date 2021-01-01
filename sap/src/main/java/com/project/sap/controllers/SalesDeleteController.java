package com.project.sap.controllers;

import com.project.sap.services.interfaces.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class SalesDeleteController {

    SalesService salesService;

    @Autowired
    public SalesDeleteController(SalesService salesService) {
        this.salesService = salesService;
    }

    @GetMapping("/delete/sales/{id}")
    public String editUser(@PathVariable(value = "id") String id){
        long currentId = Long.parseLong(id);
        salesService.deleteById(currentId);
        return "redirect:/home";
    }
}
