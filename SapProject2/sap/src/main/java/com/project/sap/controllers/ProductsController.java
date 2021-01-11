package com.project.sap.controllers;

import com.project.sap.models.*;
import com.project.sap.models.Dto.LaptopDto;
import com.project.sap.services.interfaces.AddComponentsService;
import com.project.sap.services.interfaces.LaptopService;
import com.project.sap.services.interfaces.SalesService;
import com.project.sap.utils.InputValidation;
import com.project.sap.utils.LaptopMapperResolver;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ProductsController {

    private SalesService salesService;
    private LaptopService laptopService;
    AddComponentsService addComponentsService;
    LaptopMapperResolver laptopMapper;

    @Autowired
    public ProductsController(LaptopService laptopService, AddComponentsService addComponentsService, LaptopMapperResolver laptopMapper, SalesService salesService) {
        this.laptopService = laptopService;
        this.addComponentsService = addComponentsService;
        this.laptopMapper = laptopMapper;
        this.salesService = salesService;
    }

    @GetMapping("/add-product")
    public ModelAndView addProduct(Model model){
        ModelAndView mav = new ModelAndView("addproduct");
        mav.addObject("ramsList", addComponentsService.getRams());
        mav.addObject("processorList", addComponentsService.getProcessors());
        mav.addObject("screenList", addComponentsService.getScreens());
        mav.addObject("storageList", addComponentsService.getStorages());
        mav.addObject("videoCardList", addComponentsService.getVideoCards());
        mav.addObject("laptop", new LaptopDto());
        return mav;
    }

    @PostMapping("/add-product")
    public ModelAndView saveProduct(LaptopDto laptopDto, Model model){
        InputValidation.lenghtCheck("Manufacturer",laptopDto.getManufacturer(), model);
        InputValidation.lenghtCheck("Model", laptopDto.getModel(), model);
        InputValidation.numberCheck("Price", laptopDto.getPrice(), model);
        if (model.getAttribute("error") != null){
            return this.addProduct(model);
        }
        Laptop laptop = laptopMapper.laptopDtoToLaptop(laptopDto);
        laptopService.add(laptop);
        return new ModelAndView("redirect:/home");
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
        laptop.setSales(laptopService.findById(laptopDto.getId()).get().getSales());
        laptop.setId(laptopDto.getId());
        for (Sale sale: salesService.getAll().stream().filter(x->x.getLaptop().getId()==laptop.getId()).collect(Collectors.toList())) {
            sale.setTotalPrice(BigDecimal.valueOf(laptop.getPrice()*sale.getQuantity()));
            salesService.save(sale);
        }

        laptopService.add(laptop);
        return "redirect:/products";
    }

    @GetMapping("/products")
    public ModelAndView getProducts(){
        ModelAndView mav = new ModelAndView("products");

        List<Laptop> laptopList = laptopService.get();

        mav.addObject("products", laptopList);
        return mav;

    }
}
