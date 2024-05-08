package com.example.apischedulefinancialtransfer.dtos;

import java.io.Serializable;

public class TokenResponseDTO implements Serializable {
    private String token;

    public TokenResponseDTO(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
