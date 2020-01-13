package com.it.configuration;

import com.it.security.SecurityConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

//crud+
//dto+
//mapper+
//db+
//validate+- messages
//exceptions+ //Controller - Exc - ControllerAdvice - Logger+ //security - conf - Exc - AccessDeniedHandler - Logger+ //security - conf - Exc - AuthEntryPoint - Logger+ //security - filter - Exc - Logger+
//logger+
//spring security+

@Configuration
@ComponentScan(basePackages = "com.it")
@Import({WebConfiguration.class, DatabaseConfiguration.class, SecurityConfiguration.class, MessagesConfiguration.class})
//logback.xml
//ValidationMessages.properties
public class AppConfiguration {

}
