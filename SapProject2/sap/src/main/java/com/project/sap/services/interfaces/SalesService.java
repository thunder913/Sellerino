package com.project.sap.services.interfaces;

import com.project.sap.models.Dto.SaleDto;
import com.project.sap.models.Sale;

import java.util.List;

public interface SalesService {
    List<Sale> getAll();

    void save(Sale sale);

    void deleteById(long id);

    Sale getById(long id);

    boolean saleWithProductExists(long productId);
}
