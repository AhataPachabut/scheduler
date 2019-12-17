package com.it.configuration;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

//view resolver (сопоставляет методы контроллеров и views)
@EnableWebMvc
public class WebConfiguration {

    @Bean
    public Mapper mapper() {
        return new DozerBeanMapper();
    }

}
