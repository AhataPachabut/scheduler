package com.it.converter;

import com.it.service.ServiceService;
import org.dozer.CustomConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceConverter implements CustomConverter {

    private static ServiceService serviceService;

    @Autowired
    public ServiceConverter(ServiceService serviceService){
        ServiceConverter.serviceService = serviceService;
    }

    public ServiceConverter(){}

    @Override
    public Object convert(Object dest, Object source, Class<?> destinationClass, Class<?> sourceClass) {

        if (source == null)
            return null;

        else if (source instanceof Long) {
            return serviceService.findById((Long) source);
        } else return null;
    }
}
