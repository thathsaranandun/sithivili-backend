package com.example.demo.exception.handler.errors;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class APIError {
    private HttpStatus status;
    private int statusCode;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private String message;
    private String debugMessage;
    private String tag;

    private APIError() {
        timestamp = LocalDateTime.now();
    }

    public APIError(HttpStatus status, int statusCode) {
        this();
        this.status = status;
        this.statusCode = statusCode;
    }

    public APIError(HttpStatus status,int statusCode, Throwable ex) {
        this();
        this.status = status;
        this.message = "Unexpected error";
        this.debugMessage = ex.getLocalizedMessage();
        this.statusCode = statusCode;

    }

    public APIError(HttpStatus status,int statusCode, String message, Throwable ex) {
        this();
        this.status = status;
        this.statusCode = statusCode;
        this.message = message;
        this.debugMessage = ex.getLocalizedMessage();
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDebugMessage() {
        return debugMessage;
    }

    public void setDebugMessage(String debugMessage) {
        this.debugMessage = debugMessage;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
