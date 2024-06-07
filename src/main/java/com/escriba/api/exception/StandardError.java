package com.escriba.api.exception;

import lombok.Data;

@Data
public class StandardError {
    private Integer status;
    private String message;
    private Long timestamp;

    public StandardError(Integer status, String message, Long timestamp) {
        this.status = status;
        this.message = message;
        this.timestamp = timestamp;
    }
}
