package com.skepseis.service.exception.handler;

import com.skepseis.service.exception.UnauthorizedException;
import com.skepseis.service.exception.handler.errors.APIError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    private static  final Logger LOGGER = LoggerFactory.getLogger(RestExceptionHandler.class);


    @ExceptionHandler(UnauthorizedException.class)
    protected ResponseEntity<Object> handleUnauthorizedAccessException(UnauthorizedException ex){
        LOGGER.error("EXP {}", ex);

        APIError apiError = new APIError(UNAUTHORIZED,UNAUTHORIZED.value());
        apiError.setMessage(ex.getLocalizedMessage());
        apiError.setDebugMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }

    private ResponseEntity<Object> buildResponseEntity(APIError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

}
