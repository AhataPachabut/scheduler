package com.it.component;

import org.springframework.stereotype.Component;

@Component
public class Validator {

    public void validate(boolean expression, String errorMessage) {
        if (expression) {
            throw new RuntimeException(errorMessage);
        }
    }
}
