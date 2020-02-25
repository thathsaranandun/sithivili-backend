package com.example.demo.security;

import com.example.demo.response.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {

        response.setContentType("application/json") ;
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED) ;

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setResponse("Authentication Failed");
        errorResponse.setResponseCode("01");
        final ObjectMapper mapper = new ObjectMapper() ;
        mapper.writeValue(response.getOutputStream(), errorResponse) ;
    }
}