package com.it.security.filter;

import com.it.security.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//our filter
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    private static final String AUTHORIZATION = "Authorization";
    private static final String BEARER = "Bearer ";

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        //get encrypted token from header
        String token = getToken(httpServletRequest);
        //
        if (token != null && tokenService.validate(token)) {
            //get username from decrypted token
            String username = tokenService.extractUsername(token);
            //get user from username
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            //set an authenticated user
            //либо лог+парль либо userdet+request т.к. в пер
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    //we get string. it's encrypted by MD5 JSON with username and
    private String getToken(HttpServletRequest request) {
        String authHeader = request.getHeader(AUTHORIZATION);
        return authHeader != null && authHeader.startsWith(BEARER)
                ? authHeader.replace(BEARER, "") : authHeader;
    }
}
