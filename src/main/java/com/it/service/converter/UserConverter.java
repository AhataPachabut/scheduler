package com.it.service.converter;

import com.it.service.UserService;
import org.dozer.CustomConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type User converter.
 */
@Service
public class UserConverter implements CustomConverter {

    private static UserService userService;

    /**
     * Instantiates a new User converter.
     *
     * @param userService the user service
     */
    @Autowired
    public UserConverter(UserService userService) {
        UserConverter.userService = userService;
    }

    /**
     * Instantiates a new User converter.
     */
    public UserConverter(){}

    @Override
    public Object convert(Object dest, Object source, Class<?> destinationClass, Class<?> sourceClass) {

        if (source == null)
            return null;

        else if (source instanceof Long) {
            return userService.findById((Long) source);
        }

        else return null;
    }
}
