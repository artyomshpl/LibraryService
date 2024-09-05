package com.shep.mappers;

import com.shep.dto.FreeBookDTO;
import com.shep.entities.FreeBook;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FreeBookMapper {
    FreeBookMapper INSTANCE = Mappers.getMapper(FreeBookMapper.class);

    FreeBookDTO toDto(FreeBook freeBook);
    FreeBook toEntity(FreeBookDTO freeBookDto);
}