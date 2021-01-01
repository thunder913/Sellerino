package com.project.sap.utils;

import com.project.sap.models.Dto.LaptopDto;
import com.project.sap.models.Laptop;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

public interface LaptopMapper {
    LaptopMapper INSTANCE = Mappers.getMapper(LaptopMapper.class);

    Laptop laptopDtoToLaptop(LaptopDto laptopDto);
    LaptopDto laptopToLaptopDto(Laptop laptop);
}
