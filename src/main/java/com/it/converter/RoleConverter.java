package com.it.converter;

import com.it.service.RoleService;
import org.dozer.CustomConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoleConverter implements CustomConverter {

    private static RoleService roleService;

    @Autowired
    public RoleConverter(RoleService roleService){
        RoleConverter.roleService = roleService;
    }

    public RoleConverter(){}

    @Override
    public Object convert(Object dest, Object source, Class<?> destinationClass, Class<?> sourceClass) {

        if (source == null)
            return null;

        else if (source instanceof Set) {
            return ((Set)source).stream()
                    .map((i) -> roleService.findById((Long) i))
                    .collect(Collectors.toSet());
        }

        else return null;
    }
}
