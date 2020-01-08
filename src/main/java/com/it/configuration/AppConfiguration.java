package com.it.configuration;

import com.it.security.SecurityConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

//crud+
//dto+
//mapper+- autowired service
//db+
//exceptions+- not all
//validate+- messages
//logger- //Controller - Exc - ControllerAdvice - Logger+ //security - filter - Exc - Logger+ //test it
//spring security+- roles joinfetch //role_admin+ //

@Configuration
@ComponentScan(basePackages = "com.it")
@Import({WebConfiguration.class, DatabaseConfiguration.class, SecurityConfiguration.class, MessagesConfiguration.class})
public class AppConfiguration {

}
