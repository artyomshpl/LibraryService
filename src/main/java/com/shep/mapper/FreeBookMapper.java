package com.shep.mapper;

import com.shep.dataTransferObjects.FreeBookDTO;
import com.shep.entities.FreeBook;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FreeBookMapper {
    FreeBookMapper INSTANCE = Mappers.getMapper(FreeBookMapper.class);

    FreeBookDTO toDto(FreeBook freeBook);
    FreeBook toEntity(FreeBookDTO freeBookDto);
}