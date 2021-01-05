package com.project.sap.controllers;

import com.project.sap.models.Dto.LaptopDto;
import com.project.sap.models.Laptop;
import com.project.sap.models.Sale;
import com.project.sap.services.interfaces.AddComponentsService;
import com.project.sap.services.interfaces.LaptopService;
import com.project.sap.services.interfaces.SalesService;
import com.project.sap.utils.InputValidation;
import com.project.sap.utils.LaptopMapperResolver;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.util.stream.Collectors;

@Controller
public class ProductsEditController {

    AddComponentsService addComponentsService;
    LaptopService laptopService;
    SalesService salesService;
    LaptopMapperResolver laptopMapper;
    @Autowired
    public ProductsEditController(AddComponentsService addComponentsService, LaptopService laptopService, SalesService salesService, LaptopMapperResolver laptopMapper ) {
        this.addComponentsService = addComponentsService;
        this.laptopService = laptopService;
        this.salesService = salesService;
        this.laptopMapper = laptopMapper;
    }

    @GetMapping("edit/products/{id}")
    public ModelAndView editProduct(@PathVariable(value ="id") String id){
        long currentId = Long.parseLong(id);
        Laptop laptop = laptopService.findById(currentId).get();
        ModelAndView mav = new ModelAndView("edit-product");
        LaptopDto laptopDto = laptopMapper.laptopToLaptopDto(laptop);
        mav.addObject("imageList", laptop.getImages());
        mav.addObject("product", laptopDto);
        mav.addObject("ramsList", addComponentsService.getRams());
        mav.addObject("processorList", addComponentsService.getProcessors());
        mav.addObject("screenList", addComponentsService.getScreens());
        mav.addObject("storageList", addComponentsService.getStorages());
        mav.addObject("videoCardList", addComponentsService.getVideoCards());

        return mav;
    }

    @PostMapping("edit/products/{id}")
    public String submitEditProduct(@PathVariable(value = "id") String id, LaptopDto laptopDto, RedirectAttributes ra){
        if (!NumberUtils.isCreatable(laptopDto.getPrice()) || Double.parseDouble(laptopDto.getPrice())<=0){
            ra.addFlashAttribute("error", "The price cannot be equal or less than 0!");
            return "redirect:/edit/products/" + id;
        }
        Laptop laptop = laptopMapper.laptopDtoToLaptop(laptopDto);
        laptop.setId(laptopDto.getId());
        for (Sale sale: salesService.getAll().stream().filter(x->x.getLaptop().getId()==laptop.getId()).collect(Collectors.toList())) {
            sale.setTotalPrice(BigDecimal.valueOf(laptop.getPrice()*sale.getQuantity()));
            salesService.save(sale);
        }
        laptopService.add(laptop);
        return "redirect:/products";
    }
}
