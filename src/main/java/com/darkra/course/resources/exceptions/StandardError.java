package com.darkra.course.resources.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.time.Instant;

//class used to customize an error message in JSON

public class StandardError implements Serializable {

    private static final long serialVersionUID = 1L;

    //attribute used to represent the exact moment of the error
    //annotation used to define the JSON serialization format
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'",timezone = "GMT")
    private Instant timeStamp;

    //attribute that represents the error code like 404, 200 and 500
    private Integer status;

    //attribute that represents the error description like "Not found" and "Internet Server Error"
    private String error;

    //attribute that details the error reason
    private String message;

    //attribute that carries the endpoint that caused the error
    private String path;

    //constructors
    public StandardError(){
    }

    public StandardError(Instant timeStamp, Integer status, String error, String message, String path) {
        this.timeStamp = timeStamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }

    //getters and setters method
    public Instant getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Instant timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
