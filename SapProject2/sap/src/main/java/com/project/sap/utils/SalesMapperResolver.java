package com.project.sap.utils;

import com.project.sap.models.Dto.SaleDto;
import com.project.sap.models.Sale;
import com.project.sap.repositories.SaleRepository;
import com.project.sap.services.interfaces.ClientService;
import com.project.sap.services.interfaces.LaptopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.NumberUtils;

import java.math.BigDecimal;

@Component
public class SalesMapperResolver{

    ClientService clientService;
    LaptopService laptopService;

    @Autowired
    public SalesMapperResolver(SaleRepository saleRepository, ClientService clientService, LaptopService laptopService) {
        this.clientService = clientService;
        this.laptopService = laptopService;
    }
    public Sale saleDtoToSale(SaleDto saleDto) {
        Sale sale = new Sale();
        sale.setBuyer(clientService.getById(saleDto.getBuyerId()).get());
        sale.setLaptop(laptopService.findById(saleDto.getLaptopId()).get());
        sale.setDate(saleDto.getDate());
        sale.setQuantity(Integer.parseInt(saleDto.getQuantity()));
        sale.setTotalPrice(NumberUtils.parseNumber(saleDto.getTotalPrice(), BigDecimal.class));
        return sale;
    }

    public SaleDto saleToSaleDto(Sale sale) {
        SaleDto saleDto = new SaleDto();
        saleDto.setDate(sale.getDate());
        saleDto.setBuyerId(sale.getBuyer().getId());
        saleDto.setLaptopId(sale.getLaptop().getId());
        saleDto.setId(sale.getId());
        saleDto.setPriceForOne(String.valueOf(sale.getLaptop().getPrice()));
        saleDto.setQuantity(String.valueOf(sale.getQuantity()));
        saleDto.setTotalPrice(String.valueOf(sale.getTotalPrice()));
        return saleDto;
    }

}
