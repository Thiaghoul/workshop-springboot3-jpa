package com.darkra.course.services.exceptions;

public class ResourceNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    // Constructor that takes an object (ID) and creates a custom error message
    public ResourceNotFoundException(Object id){
        // Passes a message to the RuntimeException class
        super("Resource not found. Id = " + id);
    }
}
