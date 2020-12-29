package com.project.sap.controllers;

import com.project.sap.models.Dto.dateDto;
import com.project.sap.models.Sale;
import com.project.sap.models.Statistic;
import com.project.sap.services.ClientService;
import com.project.sap.services.SalesService;
import com.project.sap.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Controller
public class SalesAdminController {

    @Autowired
    UserService userService;
    @Autowired
    ClientService clientService;
    @Autowired
    SalesService salesService;

    //TODO analyze the sales -> total price of all, total count of sale, average per sale, best seller, best client etc...
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
            Date date1=new SimpleDateFormat("MM/dd/yyyy").parse(matches.get(0));
            Date date2=new SimpleDateFormat("MM/dd/yyyy").parse(matches.get(1));
            //Adding one day to the second date, so that it is inclusive
            sales = sales.stream().filter(x->x.getDate().after(date1) && x.getDate().before(new Date(date2.getTime() + 86400000))).collect(Collectors.toList());
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
        mav.addObject("date", new dateDto());
        return mav;
    }
}
