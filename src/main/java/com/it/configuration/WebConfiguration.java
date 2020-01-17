package com.it.configuration;

import com.it.converter.*;
import com.it.dto.request.*;
import com.it.model.*;
import com.it.dto.SessionResponseDTO;
import com.it.model.security.Session;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.dozer.loader.api.FieldsMappingOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * The type Web configuration.
 */
@EnableWebMvc
public class WebConfiguration {

    /**
     * Mapper mapper.
     *
     * @return the mapper
     */
    @Bean
    public Mapper mapper() {

        final BeanMappingBuilder builder = new BeanMappingBuilder() {
            @Override
            protected void configure() {

                mapping(EventRequestDto.class, Event.class)
                        .fields("client", "client", FieldsMappingOptions.customConverter(ClientConverter.class))
                        .fields("service", "service", FieldsMappingOptions.customConverter(ServiceConverter.class))
                        .fields("resources", "resources", FieldsMappingOptions.customConverter(ResourceConverter.class));

                mapping(ClientRequestDto.class, Client.class)
                        .fields("name", "name")
                        .fields("user", "user", FieldsMappingOptions.customConverter(UserConverter.class));

                mapping(UserRequestDto.class, User.class)
                        .fields("name", "name")
                        .fields("password", "password")
                        .fields("roles", "roles", FieldsMappingOptions.customConverter(RoleConverter.class));

                mapping(Session.class, SessionResponseDTO.class)
                        .fields("accessToken.key", "accessToken")
                        .fields("refreshToken.key", "refreshToken");
            }
        };

        final DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();
        dozerBeanMapper.addMapping(builder);
        return dozerBeanMapper;
    }
}

