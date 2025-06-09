package com.workintech.zoo.exceptions;


import org.springframework.http.HttpStatus;

public class ZooException extends RuntimeException {
    private HttpStatus httpstatus;

    public ZooException(String message) {
        super(message);
        this.httpstatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public ZooException(String message, HttpStatus httpstatus) {
        super(message);
        this.httpstatus = httpstatus;
    }

    public HttpStatus getHttpStatus() {
        return httpstatus;
    }
    public void setHttpStatus(HttpStatus httpstatus) {
        this.httpstatus = httpstatus;
    }

}