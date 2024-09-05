package com.shep.config;

import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.shep.mappers.FreeBookMapper;

@Configuration
public class MapperConfig {

    @Bean
    public FreeBookMapper freeBookMapper() {
        return Mappers.getMapper(FreeBookMapper.class);
    }
}