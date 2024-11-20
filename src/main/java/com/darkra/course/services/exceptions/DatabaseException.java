package com.darkra.course.services.exceptions;

public class DatabaseException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    //constructor that takes a message and passes it to the RuntimeException class
    public DatabaseException(String message){
        super(message);
    }
}
