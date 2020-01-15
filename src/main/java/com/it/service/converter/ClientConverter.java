package com.it.service.converter;

import com.it.service.ClientService;
import org.dozer.CustomConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type Client converter.
 */
@Service
public class ClientConverter implements CustomConverter {

    private static ClientService clientService;

    /**
     * Instantiates a new Client converter.
     *
     * @param clientService the client service
     */
    @Autowired
    public ClientConverter(ClientService clientService) {
        ClientConverter.clientService = clientService;
    }

    /**
     * Instantiates a new Client converter.
     */
    public ClientConverter() {
    }

    @Override
    public Object convert(Object dest, Object source, Class<?> destinationClass, Class<?> sourceClass) {

        if (source == null)
            return null;

        else if(source instanceof Long) {
            return clientService.findById((Long) source);
        }

        else return null;
    }
}
