package com.project.sap.utils;

import com.project.sap.models.Dto.ProcessorDto;
import com.project.sap.models.Processor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProcessorMapper {
    ProcessorMapper INSTANCE = Mappers.getMapper(ProcessorMapper.class);
    Processor processorDtoToProcessor(ProcessorDto processorDto);
}
