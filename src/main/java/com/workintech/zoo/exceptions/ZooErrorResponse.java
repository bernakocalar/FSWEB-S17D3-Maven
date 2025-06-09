package com.workintech.zoo.exceptions;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ZooErrorResponse {
    // Getters and setters
    private String message;
    private int status;
    private long timestamp;

    public ZooErrorResponse() {
    }

    public ZooErrorResponse( int status, String message, long timestamp) {
        this.message = message;
        this.status = status;
        this.timestamp = timestamp;
    }

}
