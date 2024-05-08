package com.example.apischedulefinancialtransfer.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class AuthenticationDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank
    @NotNull
    private String email;
    @NotBlank
    @NotNull
    private String password;

    public AuthenticationDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public @NotBlank @NotNull String getEmail() {
        return email;
    }

    public @NotBlank @NotNull String getPassword() {
        return password;
    }
}
