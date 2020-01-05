package com.it.converter;

import com.it.service.ClientService;
import com.it.service.ServiceService;
import org.dozer.CustomConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceConverter implements CustomConverter {

    private ServiceService serviceRepository;

    @Autowired
    public ServiceConverter(ClientService clientRepository){
        this.serviceRepository = serviceRepository;
    }

    public ServiceConverter(){}

    @Override
    public Object convert(Object dest, Object source, Class<?> destinationClass, Class<?> sourceClass) {

        if (source == null)
            return null;

        else if (source instanceof Long) {
            return serviceRepository.findById((Long) source);
        } else return null;
    }
}
