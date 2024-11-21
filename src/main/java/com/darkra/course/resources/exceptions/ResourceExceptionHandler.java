package com.darkra.course.resources.exceptions;

import com.darkra.course.services.exceptions.DatabaseException;
import com.darkra.course.services.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.hibernate.dialect.Database;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

//class responsible for managing specified exceptions
//annotation used for Spring be able to recognize this class as a global error manager
@ControllerAdvice
public class ResourceExceptionHandler {

    /**
     * Method to handle ResourceNotFoundException.
     * Returns an HTTP response with code 404 (NOT FOUND) and details about the error.
     *
     * @param e       THe exception caught.
     * @param request The object that contains information about the HTTP request.
     * @return A ResponseEntity containing formatted error.
     */

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e,
                                                          HttpServletRequest request){
        //the error description
        String error = "Resource not found.";

        //the error status
        HttpStatus status = HttpStatus.NOT_FOUND;

        //the creation of the StandardError object with the details of the error
        StandardError err = new StandardError(Instant.now(), status.value(), error,
                e.getMessage(), request.getRequestURI());

        //returns the error with the corresponding HTTP code
        return ResponseEntity.status(status).body(err);
    }

    /**
     * Method to handle DatabaseException.
     * Returns an HTTP response with code 400 (BAD REQUEST) and details about the error.
     *
     * @param e       The exception caught.
     * @param request the object that contains information about the HTTP request.
     * @return A ResponseEntity containing formatted error.
     */

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<StandardError> database(DatabaseException e,
                                                  HttpServletRequest request){

        //the error description
        String error = "Database error.";

        //the error status
        HttpStatus status = HttpStatus.BAD_REQUEST;

        //the creation of the StandardError object with the details of the error
        StandardError err = new StandardError(Instant.now(), status.value(), error,
                e.getMessage(), request.getRequestURI());

        //returns the error with the corresponding HTTP code
        return ResponseEntity.status(status).body(err);
    }
}
