package com.sme.app.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(Long id) {
        super("No resource found with ID " + id);
    }
}
