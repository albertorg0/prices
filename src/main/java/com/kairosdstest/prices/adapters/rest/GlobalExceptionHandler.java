package com.kairosdstest.prices.adapters.rest;

import com.kairosdstest.prices.core.NoResultsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

/**
 * Global exception handler for the REST layer.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleMissingServletRequestParameter(MissingServletRequestParameterException ex) {
        String errorMessage = "Missing parameter: " + ex.getParameterName();
        return ErrorResponse.builder().code(HttpStatus.BAD_REQUEST.value()).message(errorMessage).build();
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex) {
        String errorMessage = "Invalid parameter type: " + ex.getParameter().getParameterName();
        return ErrorResponse.builder().code(HttpStatus.BAD_REQUEST.value()).message(errorMessage).build();
    }

    @ExceptionHandler(NoResultsException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNoResults(NoResultsException ex) {
        return ErrorResponse.builder().code(HttpStatus.NOT_FOUND.value()).message(ex.getMessage()).build();
    }
}