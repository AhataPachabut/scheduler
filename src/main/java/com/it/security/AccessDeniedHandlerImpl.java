package com.it.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.it.dto.ErrorResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {

        String errorMessage = e.getMessage();

        log.error(errorMessage, e);

        //response.getWriter().print("You don't have required role to perform this action.");

        ErrorResponseDto errorResponseDTO = new ErrorResponseDto(HttpStatus.INTERNAL_SERVER_ERROR, errorMessage);
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.setContentType("application/json");
        new ObjectMapper().writeValue(response.getWriter(), errorResponseDTO);
        }
}
