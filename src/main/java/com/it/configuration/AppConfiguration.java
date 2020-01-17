package com.it.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * The type App configuration.
 */
@Configuration
@ComponentScan(basePackages = "com.it")
@Import({WebConfiguration.class, DatabaseConfiguration.class, SecurityConfiguration.class, MessagesConfiguration.class, SwaggerConfiguration.class})
public class AppConfiguration {

}
