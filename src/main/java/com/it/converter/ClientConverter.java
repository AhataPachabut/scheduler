package com.it.converter;

import com.it.service.ClientService;
import org.dozer.CustomConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientConverter implements CustomConverter {

    @Autowired
    private ClientService clientRepository;

//    @Autowired
//    public ClientConverter(ClientService clientRepository){
//        this.clientRepository = clientRepository;
//    }

    //public ClientConverter(){}

    @Override
    public Object convert(Object dest, Object source, Class<?> destinationClass, Class<?> sourceClass) {

        if (source == null)
            return null;

        else if(source instanceof Long) {
            return clientRepository.findById((Long) source);
        }

        else return null;
    }
}
