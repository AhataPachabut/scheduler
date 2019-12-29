package com.it.security.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.token.Token;
import org.springframework.security.core.token.TokenService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//our filter. must be been. чтобы сработал @Autowired
@Component
 public class TokenAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(TokenAuthenticationFilter.class);

    private static final String AUTHORIZATION = "Authorization";

//    @Autowired
//    private TokenService tokenService;
//
//    @Autowired
//    private UserDetailsService userDetailsService;

    private TokenService tokenService;

    private UserDetailsService userDetailsService;

    @Autowired
    public TokenAuthenticationFilter(TokenService tokenService, UserDetailsService userDetailsService) {
        this.tokenService = tokenService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        try {

            //get encrypted token from header
            String tokenKey = getToken(httpServletRequest);
            if (tokenKey != null) {
                Token token = tokenService.verifyToken(tokenKey);
                String username = token.getExtendedInformation();

                //get user from username
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                //set an authenticated user
                //либо лог+парль либо userdet+request т.к. в пер
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            throw e;
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    //we get string. it's encrypted by MD5 JSON with username and
    private String getToken(HttpServletRequest request) {
        return request.getHeader(AUTHORIZATION);
    }
}
