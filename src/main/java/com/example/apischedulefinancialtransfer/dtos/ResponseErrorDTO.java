package com.example.apischedulefinancialtransfer.dtos;

public class ResponseErrorDTO {
    private String message;

    public ResponseErrorDTO(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
