package com.it.converter;

import com.it.service.ClientService;
import com.it.service.UserService;
import org.dozer.CustomConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserConverter implements CustomConverter {

    private UserService userRepository;

    @Autowired
    public UserConverter(ClientService clientRepository){
        this.userRepository = userRepository;
    }
    public UserConverter(){}

    @Override
    public Object convert(Object dest, Object source, Class<?> destinationClass, Class<?> sourceClass) {

        if (source == null)
            return null;

        else if (source instanceof Long) {
            return userRepository.findById((Long) source);
        }

        else return null;
    }
}
