package com.it.configuration;

import com.it.dto.response.ErrorResponseDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

//catch exceptions from all @RestController
@ControllerAdvice(annotations = RestController.class)
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    //private static final Logger LOGGER = LoggerFactory.getLogger(RestExceptionHandler.class);

    @ExceptionHandler(value = {RuntimeException.class})
    protected ResponseEntity<Object> handleRuntimeException(RuntimeException exception, WebRequest request) {

        ErrorResponseDto errorResponseDTO = new ErrorResponseDto(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        return handleExceptionInternal(exception, errorResponseDTO, new HttpHeaders(), errorResponseDTO.getHttpStatus(), request);
    }
}
