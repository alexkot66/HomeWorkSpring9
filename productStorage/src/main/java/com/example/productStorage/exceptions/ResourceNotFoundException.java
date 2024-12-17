package com.example.productStorage.exceptions;

/**
 * Товар не найден.
 */
public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
