package com.project.sap.utils;

import com.project.sap.models.Dto.StorageDto;
import com.project.sap.models.Storage;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StorageMapper {
    StorageMapper INSTANCE = Mappers.getMapper(StorageMapper.class);
    Storage storageDtoToStorage(StorageDto storageDto);
}
