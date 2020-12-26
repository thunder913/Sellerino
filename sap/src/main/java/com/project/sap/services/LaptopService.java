package com.project.sap.services;

import com.project.sap.models.Laptop;
import com.project.sap.models.Dto.LaptopDto;

import java.util.List;
import java.util.Optional;

public interface LaptopService {
    List<Laptop> get();

    void add(Laptop item);

    void deleteById(long id);

    Optional<Laptop> findById(long id);

    Laptop mapDtoToLaptop(LaptopDto laptopDto);

    LaptopDto mapLaptopToDto(Laptop laptop);
}
