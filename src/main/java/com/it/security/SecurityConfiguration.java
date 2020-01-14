package com.it.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

//UI - TokenFilter - LoginPassFilter - AuthController - /auth/register - return new user dto
//UI + login + pass - TokenFilter - LoginPassFilter - AuthController - /auth/signin - (auth by LoginPass) - return new access+refresh token
//UI + access token - TokenFilter - (auth by Token) - LoginPassFilter - Controller - return dto
//UI + refresh token - TokenFilter - AuthController - /auth/refreshtoken - return new access+refresh token

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private TokenAuthenticationFilter tokenAuthenticationFilter;
    @Autowired
    private AccessDeniedHandler accessDeniedHandler;
    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;

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
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .cors()

                .and()
                .authorizeRequests()

                .antMatchers("/auth/**").permitAll()

                .antMatchers(HttpMethod.POST,"/users/**").permitAll()
//                .antMatchers(HttpMethod.PUT,"/users/**").hasRole("ADMIN")
//                .antMatchers(HttpMethod.DELETE,"/users/**").hasRole("ADMIN")

                .antMatchers("/events/**").authenticated()

                .antMatchers(HttpMethod.POST).hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT).hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE).hasRole("ADMIN")

                .anyRequest().authenticated()

                //check roles
                .and()
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler)

                //check autorization
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint)

                .and()
                .addFilterBefore(tokenAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
