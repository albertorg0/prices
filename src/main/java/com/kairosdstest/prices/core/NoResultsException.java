package com.kairosdstest.prices.core;

/**
 * Exception thrown when no results are found.
 */
public class NoResultsException extends RuntimeException {

    public NoResultsException(String message) {
        super(message);
    }
}