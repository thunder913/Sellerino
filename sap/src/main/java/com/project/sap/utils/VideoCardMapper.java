package com.project.sap.utils;

import com.project.sap.models.Dto.VideoCardDto;
import com.project.sap.models.VideoCard;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VideoCardMapper {
    VideoCardMapper INSTANCE = Mappers.getMapper(VideoCardMapper.class);
    VideoCard videoCardDtoToVideoCard(VideoCardDto videoCardDto);
}
