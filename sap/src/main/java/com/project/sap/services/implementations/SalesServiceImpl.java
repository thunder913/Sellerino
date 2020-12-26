package com.project.sap.services.implementations;

import com.project.sap.models.Sale;
import com.project.sap.repositories.SaleRepository;
import com.project.sap.services.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalesServiceImpl implements SalesService {
    @Autowired
    SaleRepository saleRepository;
    @Override
    public List<Sale> getAll() {
        return saleRepository.findAll();
    }
}
