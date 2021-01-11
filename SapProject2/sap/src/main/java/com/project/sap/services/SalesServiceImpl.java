package com.project.sap.services;

import com.project.sap.models.Dto.SaleDto;
import com.project.sap.models.Sale;
import com.project.sap.repositories.SaleRepository;
import com.project.sap.services.interfaces.ClientService;
import com.project.sap.services.interfaces.LaptopService;
import com.project.sap.services.interfaces.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class SalesServiceImpl implements SalesService {

    SaleRepository saleRepository;

    @Autowired
    public SalesServiceImpl(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    @Override
    public List<Sale> getAll() {
        return saleRepository.findAll();
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

    //Read them from the DB with SQL query somehow, would be much more optimised

    @Override
    public boolean saleWithProductExists(long productId) {
        return this.getAll().stream().anyMatch(x->x.getLaptop().getId()==productId);
    }
}
