package com.it.configuration;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
public class WebConfiguration {

    @Bean
    public Mapper mapper() {
        return new DozerBeanMapper();

        /*final DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();

         class EventRequestDtoConverter extends DozerConverter<EventRequestDto, Event>
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
        }

        final BeanMappingBuilder builder = new BeanMappingBuilder() {
            @Override
            protected void configure() {
                mapping(EventRequestDto.class, Event.class)
                        .fields("clientId", "client", FieldsMappingOptions.customConverter(EventRequestDtoConverter.class))
                        .fields("serviceId", "partitionKey", FieldsMappingOptions.copyByReference())
                        .fields("source_type", "sourceType", FieldsMappingOptions.copyByReference());
            }
        };
        dozerBeanMapper.addMapping(builder);

        return dozerBeanMapper;*/
    }

}
