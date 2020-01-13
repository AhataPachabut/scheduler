package com.it.converter;

import com.it.service.ResourceService;
import org.dozer.CustomConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ResourceConverter implements CustomConverter {

    private static ResourceService resourceService;

    @Autowired
    public ResourceConverter(ResourceService resourceService) {
        ResourceConverter.resourceService = resourceService;
    }

    public ResourceConverter(){}

   @Override
    public Object convert(Object dest, Object source, Class<?> destinationClass, Class<?> sourceClass) {

        if (source == null)
            return null;

        else if (source instanceof Set) {
            return ((Set)source).stream()
                .map((i) -> resourceService.findById((Long) i))
                .collect(Collectors.toSet());
        }

        else return null;
    }
}
