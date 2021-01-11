package com.project.sap.utils;

import com.project.sap.models.Dto.UserDto;
import com.project.sap.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    User userDtoToUser(UserDto userDto);
}
