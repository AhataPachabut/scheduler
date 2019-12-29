package com.it.configuration;

import com.it.security.dto.SessionResponseDTO;
import com.it.security.model.Session;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.dozer.loader.api.FieldsMappingOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
public class WebConfiguration {

    @Bean
    public Mapper mapper() {

         /*class EventRequestDtoConverter extends DozerConverter<EventRequestDto, Event>
        {

            public EventRequestDtoConverter(Class<EventRequestDto> prototypeA, Class<Event> prototypeB) {
                super(prototypeA, prototypeB);
            }

            @Override
            public Event convertTo(EventRequestDto eventRequestDto, Event event) {
                return null;
            }

            @Override
            public EventRequestDto convertFrom(Event event, EventRequestDto eventRequestDto) {
                return null;
            }
        }*/

        final BeanMappingBuilder builder = new BeanMappingBuilder() {
            @Override
            protected void configure() {
//                mapping(EventRequestDto.class, Event.class)
//                        .fields("clientId", "client", FieldsMappingOptions.customConverter(EventRequestDtoConverter.class))
//                        .fields("serviceId", "partitionKey", FieldsMappingOptions.copyByReference())
//                        .fields("source_type", "sourceType", FieldsMappingOptions.copyByReference());

                mapping(Session.class, SessionResponseDTO.class)
                        .fields("accessToken.key", "accessToken", FieldsMappingOptions.copyByReference())
                        .fields("refreshToken.key", "refreshToken", FieldsMappingOptions.copyByReference());
            }
        };

        final DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();
        dozerBeanMapper.addMapping(builder);
        return dozerBeanMapper;
    }
}
