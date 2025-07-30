package com.devtiro.tasks.controllers;

import com.devtiro.tasks.domain.entities.TaskList;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<ErrorResponse> handleException(RuntimeException ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse() {
            @Override
            public HttpStatusCode getStatusCode() {
                return null;
            }

            @Override
            public ProblemDetail getBody() {
                return null;
            }
        };
        return new  ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
