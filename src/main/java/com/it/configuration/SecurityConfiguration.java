package com.it.configuration;

import com.it.security.filter.TokenAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //we can configure HttpSec/Webec/smth else
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .authorizeRequests()
                .antMatchers("/**").permitAll();
//                .csrf().disable().cors()
//                .and()
//                .authorizeRequests()
//                .mvcMatchers("/auth/**").permitAll()
                //.mvcMatchers(HttpMethod.GET, "/roles/**", "/users/**").hasAnyRole("ADMIN", "USER")
                //.anyRequest().hasRole("ADMIN")
                ;

        http.addFilterBefore(new TokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
