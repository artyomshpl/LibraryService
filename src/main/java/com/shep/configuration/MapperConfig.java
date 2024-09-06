package com.shep.configuration;

import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.shep.mapper.FreeBookMapper;

@Configuration
public class MapperConfig {

    @Bean
    public FreeBookMapper freeBookMapper() {
        return Mappers.getMapper(FreeBookMapper.class);
    }
}