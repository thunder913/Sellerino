package com.project.sap.utils;

import com.project.sap.models.Dto.ScreenDto;
import com.project.sap.models.Screen;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


public interface ScreenMapper {

    ScreenMapper INSTANCE = Mappers.getMapper(ScreenMapper.class);
    Screen screenDtoToScreen(ScreenDto screenDto);
}
