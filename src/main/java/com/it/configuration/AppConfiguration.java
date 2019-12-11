package com.it.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = "com.it")
@Import({WebConfiguration.class, DatabaseConfiguration.class})
public class AppConfiguration {

}
