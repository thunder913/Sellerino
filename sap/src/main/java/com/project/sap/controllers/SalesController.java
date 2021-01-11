package com.project.sap.controllers;

import com.project.sap.models.Dto.DateDto;
import com.project.sap.models.Dto.SaleDto;
import com.project.sap.models.Laptop;
import com.project.sap.models.Sale;
import com.project.sap.models.Statistic;
import com.project.sap.models.User;
import com.project.sap.services.interfaces.ClientService;
import com.project.sap.services.interfaces.LaptopService;
import com.project.sap.services.interfaces.SalesService;
import com.project.sap.services.interfaces.UserService;
import com.project.sap.utils.SalesMapperResolver;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Controller
public class SalesController {

    LaptopService laptopService;
    SalesService salesService;
    ClientService clientService;
    UserService userService;
    SalesMapperResolver salesMapperResolver;
    SalesMapperResolver salesMapper;
    @Autowired
    public SalesController(LaptopService laptopService, SalesService salesService, ClientService clientService, UserService userService, SalesMapperResolver salesMapperResolver, SalesMapperResolver salesMapper) {
        this.laptopService = laptopService;
        this.salesService = salesService;
        this.clientService = clientService;
        this.userService = userService;
        this.salesMapperResolver = salesMapperResolver;
        this.salesMapper = salesMapper;
    }

    @GetMapping("/add-sale")
    public ModelAndView addSale(Model model){
        ModelAndView mav = new ModelAndView("add-sale");
        mav.addObject("sales", salesService.getAll());
        mav.addObject("laptops", laptopService.get());
        mav.addObject("clients", clientService.get());
        mav.addObject("sale", new SaleDto());
        return mav;
    }

    @PostMapping("/add-sale")
    public ModelAndView addSale(SaleDto saleDto, Model model){
        if (saleDto.getTotalPrice() == null || saleDto.getPriceForOne() == null || Integer.parseInt(saleDto.getQuantity())==0 || Double.parseDouble(saleDto.getTotalPrice())<=0.01){
            model.addAttribute("error", "The prices and quantities cannot be empty or zero!");
            return this.addSale(model);
        }
        saleDto.setDate(Date.from((LocalDateTime.now()).toInstant(ZoneOffset.UTC)));
        Sale sale = salesMapperResolver.saleDtoToSale(saleDto);
       //Laptop laptop = laptopService.findById(saleDto.getLaptopId()).get();
       //laptop.addSale(sale);
        User seller = this.getUser();
        sale.setSeller(seller);
        salesService.save(sale);
        return new ModelAndView("redirect:/home");
    }

    @GetMapping("/sales-admin")
    public ModelAndView getAdminSale(@RequestParam(required = false) String datefilter, @RequestParam(required = false) String client, @RequestParam(required = false) String seller) throws ParseException {
        List<Sale> sales = salesService.getAll();
        ModelAndView mav =  new ModelAndView("sales-admin");

        if (seller != null && !seller.equals("0")){
            long id = Long.parseLong(seller);

            sales = sales.stream().filter(x-> x.getSeller().getId() == id).collect(Collectors.toList());
        }

        //Checks whether there is selected client
        if (client != null && !client.equals("0")){
            long id = Long.parseLong(client);
            sales = sales.stream().filter(x->x.getBuyer().getId() == id).collect(Collectors.toList());
        }

        //Checks whether there is selected date range
        if (datefilter!= null && datefilter.length() > 20) {
            Pattern dateRegex = Pattern.compile("([0-9]+\\/[0-9]+\\/[0-9]+)");
            Matcher dates = dateRegex.matcher(datefilter);
            List<String> matches = new ArrayList<>();
            while (dates.find()){
                matches.add(dates.group());
            }
            java.util.Date date1=new SimpleDateFormat("MM/dd/yyyy").parse(matches.get(0));
            java.util.Date date2=new SimpleDateFormat("MM/dd/yyyy").parse(matches.get(1));
            //Adding one day to the second date, so that it is inclusive
            sales = sales.stream().filter(x->x.getDate().after(date1) && x.getDate().before(new java.util.Date(date2.getTime() + 86400000))).collect(Collectors.toList());
        }

        if (sales.size()>0) {
            Statistic stats = new Statistic();
            BigDecimal totalCost = new BigDecimal(0);
            Sale biggestSale = new Sale();
            Sale worstSale = new Sale();
            worstSale.setTotalPrice(new BigDecimal(Double.MAX_VALUE));
            for (Sale sale : sales) {
                totalCost = totalCost.add(sale.getTotalPrice());
                if (biggestSale.getTotalPrice().compareTo(sale.getTotalPrice()) < 0) {
                    biggestSale = sale;
                }
                if (worstSale.getTotalPrice().compareTo(sale.getTotalPrice()) > 0) {
                    worstSale = sale;
                }
            }
            stats.setTotalCost(totalCost);
            stats.setSalesCount(sales.size());
            BigDecimal divided = stats.getTotalCost().divide(new BigDecimal(stats.getSalesCount()), RoundingMode.HALF_UP);
            stats.setAveragePrice(divided);
            stats.setWorstSale(worstSale);
            stats.setBiggestSale(biggestSale);
            mav.addObject("stats", stats);
        }
        mav.addObject("sales", sales);
        mav.addObject("sellers", (userService.get().stream().filter(x->x.getRole().equals("SR")).collect(Collectors.toList())));
        mav.addObject("clients", clientService.get());
        mav.addObject("date", new DateDto());
        return mav;
    }

    @GetMapping("/delete/sales/{id}")
    public String editUser(@PathVariable(value = "id") String id){
        long currentId = Long.parseLong(id);
        salesService.deleteById(currentId);
        return "redirect:/home";
    }

    @GetMapping("/edit/sales/{id}")
    public ModelAndView editSale(@PathVariable(value = "id") String id){
        long currentId = Long.parseLong(id);
        Sale sale = salesService.getById(currentId);
        ModelAndView mav = new ModelAndView("edit-sale");
        mav.addObject("laptops", laptopService.get());
        mav.addObject("clients", clientService.get());
        mav.addObject("sale", salesMapper.saleToSaleDto(sale));
        return mav;
    }

    @PostMapping("/edit/sales/{id}")
    public String submitEditSale(@PathVariable(value="id") String id, SaleDto saleDto, RedirectAttributes ra){
        long currentId = Long.parseLong(id);
        if (!NumberUtils.isCreatable(saleDto.getQuantity()) || saleDto.getTotalPrice() == null || saleDto.getPriceForOne() == null || Integer.parseInt(saleDto.getQuantity())<=0){
            ra.addFlashAttribute("error", "The quantity and the price cannot be negative or empty!");
            return "redirect:/edit/sales/" + currentId;
        }
        saleDto.setDate(Date.from((LocalDateTime.now()).toInstant(ZoneOffset.UTC)));
        Sale sale = salesMapper.saleDtoToSale(saleDto);
        sale.setId(currentId);
        sale.setSeller(salesService.getById(currentId).getSeller());
        salesService.save(sale);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getAuthorities().stream().anyMatch(x->x.getAuthority().equals("Admin"))){
            return "redirect:/sales-admin";
        }else if(auth.getAuthorities().stream().anyMatch(x->x.getAuthority().equals("SR"))){
            return "redirect:/sales-sr";
        }
        return "/home";
    }

    @GetMapping("/sales-sr")
    public ModelAndView getSalesSr(){
        User user = this.getUser();
        ModelAndView mav = new ModelAndView("sales-sr");
        mav.addObject("sales", user.getSales());
        return mav;
    }

    private User getUser(){
        String username = ((Authentication) SecurityContextHolder.getContext().getAuthentication()).getName();
        User user = userService.getUserByEmail(username);
        return user;
    }
}
