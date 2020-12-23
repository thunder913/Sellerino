package com.project.sap.api;

import com.project.sap.models.Laptop;
import com.project.sap.models.RAM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
//TODO Make pages, so that not all info is loaded at once
@Controller
public class ProductsController {

    @Autowired
    DBController dbController;

    @GetMapping("/products")
    public ModelAndView getProducts(){
        ModelAndView mav = new ModelAndView("products");

        List<Laptop> laptopList = dbController.getLaptops();

        mav.addObject("products", laptopList);
        return mav;

    }

    //TODO Delete all the middle table values, so I can delete the main laptop
    @GetMapping("/delete/products/{id}")
    public String deleteUser(@PathVariable(value = "id") String id){
        long currentId = Long.parseLong(id);
        dbController.deleteLaptopById(currentId);
        return "redirect:/products";
    }
}
