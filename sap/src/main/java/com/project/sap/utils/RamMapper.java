package com.project.sap.utils;

import com.project.sap.models.Dto.RamDto;
import com.project.sap.models.RAM;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RamMapper {

    RamMapper INSTANCE = Mappers.getMapper(RamMapper.class);
    RAM ramDtoToRam(RamDto ramDto);
}
