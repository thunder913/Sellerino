package com.project.sap.controllers;

import com.project.sap.services.interfaces.LaptopService;
import com.project.sap.services.interfaces.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ProductsDeleteController {

    SalesService salesService;
    LaptopService laptopService;

    @Autowired
    public ProductsDeleteController(LaptopService laptopService, SalesService salesService) {
        this.laptopService = laptopService;
        this.salesService = salesService;
    }

    @GetMapping("/delete/products/{id}")
    public String deleteUser(@PathVariable(value = "id") String id, RedirectAttributes ra){
        long currentId = Long.parseLong(id);
        if (salesService.saleWithProductExists(currentId)){
            ra.addFlashAttribute("error", "You cannot delete a laptop who has a sale!");
        }else {
            laptopService.deleteById(currentId);
        }
        return "redirect:/products";
    }
}
