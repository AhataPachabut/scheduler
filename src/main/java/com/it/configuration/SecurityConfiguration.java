package com.it.configuration;

import com.it.security.filter.TokenAuthenticationFilter;
import com.it.security.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

//UI - TokenFilter - LoginPassFilter - AuthController - /auth/register - return new user dto
//UI + login + pass - TokenFilter - LoginPassFilter - AuthController - /auth/signin - (auth by LoginPass) - return new access+refresh token
//UI + access token - TokenFilter - (auth by Token) - LoginPassFilter - Controller - return dto
//UI + refresh token - TokenFilter - AuthController - /auth/refreshtoken - return new access+refresh token

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserDetailsService userDetailsService;

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
//                .authorizeRequests()
//                .antMatchers("/**").permitAll();
                .csrf().disable().cors()
//                .and()
//                .authorizeRequests()
//                .mvcMatchers("/auth/**").permitAll()
                //.mvcMatchers(HttpMethod.GET, "/roles/**", "/users/**").hasAnyRole("ADMIN", "USER")
                //.anyRequest().hasRole("ADMIN")
                ;

        http.addFilterBefore(new TokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
