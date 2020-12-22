package com.project.sap.api;

import com.project.sap.models.*;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AddProductController {

    @Autowired
    private DBController dbController;

    @GetMapping("add-product")
    public ModelAndView addProduct(Model model){
        ModelAndView mav = new ModelAndView("addproduct");
        mav.addObject("ramsList", dbController.getRams());
        mav.addObject("processorList", dbController.getProcessors());
        mav.addObject("screenList", dbController.getScreens());
        mav.addObject("storageList", dbController.getStorages());
        mav.addObject("videoCardList", dbController.getVideoCards());
        model.addAttribute("laptop", new LaptopDto());
        return mav;
    }

    //TODO Add images in the add-product tab
    @PostMapping("add-product")
    public String saveProduct(LaptopDto laptop){
        Processor processor = dbController.findProcessorById(laptop.getProcessorId()).get();
        Screen screen = dbController.findScreenById(laptop.getScreenId()).get();
        VideoCard videoCard = dbController.findVideoCardById(laptop.getVideoCardId()).get();

        List<RAM> rams = new ArrayList<>();
        for (long id:laptop.getRamIds()) {
            rams.add(dbController.findRamById(id).get());
        }

        List<Storage> storageList = new ArrayList<>();
        for (long id:laptop.getStorageIds()) {
            storageList.add(dbController.findStorageById(id).get());
        }

        Laptop newLaptop = (Laptop) laptop;
        newLaptop.setProcessor(processor);
        newLaptop.setScreen(screen);
        newLaptop.setVideoCard(videoCard);
        newLaptop.setRam(rams);
        newLaptop.setStorage(storageList);

        dbController.addLaptop(newLaptop);
        return "redirect:/home";
    }
}
