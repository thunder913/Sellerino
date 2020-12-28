package com.project.sap.services.implementations;

import com.project.sap.models.Dto.SaleDto;
import com.project.sap.models.Sale;
import com.project.sap.repositories.SaleRepository;
import com.project.sap.services.ClientService;
import com.project.sap.services.LaptopService;
import com.project.sap.services.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class SalesServiceImpl implements SalesService {
    @Autowired
    SaleRepository saleRepository;
    @Autowired
    ClientService clientService;
    @Autowired
    LaptopService laptopService;

    @Override
    public List<Sale> getAll() {
        return saleRepository.findAll();
    }

    @Override
    public Sale convertDtoToSale(SaleDto saleDto) {
        Sale sale = new Sale();
        sale.setBuyer(clientService.getById(saleDto.getBuyerId()).get());
        sale.setLaptop(laptopService.findById(saleDto.getLaptopId()).get());
        sale.setDate(saleDto.getDate());
        sale.setQuantity(saleDto.getQuantity());
        sale.setTotalPrice(saleDto.getTotalPrice());
        return sale;
    }

    @Override
    public SaleDto convertSaleToDto(Sale sale) {
        SaleDto saleDto = new SaleDto();
        saleDto.setDate(sale.getDate());
        saleDto.setBuyerId(sale.getBuyer().getId());
        saleDto.setLaptopId(sale.getLaptop().getId());
        saleDto.setId(sale.getId());
        saleDto.setPriceForOne(BigDecimal.valueOf(sale.getLaptop().getPrice()));
        saleDto.setQuantity(sale.getQuantity());
        saleDto.setTotalPrice(sale.getTotalPrice());
        return saleDto;
    }

    @Override
    public void save(Sale sale) {
        saleRepository.save(sale);
    }

    @Override
    public void deleteById(long id) {
        saleRepository.deleteById(id);
    }

    @Override
    public Sale getById(long id) {
        return saleRepository.getOne(id);
    }
}
