package com.it.service.converter;

import com.it.service.ServiceService;
import org.dozer.CustomConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type Service converter.
 */
@Service
public class ServiceConverter implements CustomConverter {

    private static ServiceService serviceService;

    /**
     * Instantiates a new Service converter.
     *
     * @param serviceService the service service
     */
    @Autowired
    public ServiceConverter(ServiceService serviceService){
        ServiceConverter.serviceService = serviceService;
    }

    /**
     * Instantiates a new Service converter.
     */
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
