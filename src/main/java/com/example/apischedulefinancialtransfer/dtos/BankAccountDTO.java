package com.example.apischedulefinancialtransfer.dtos;

import com.example.apischedulefinancialtransfer.models.UserModel;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class BankAccountDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank
    @NotNull
    @Size(max = 10, min = 10)
    private String bankAccountDefault;
    @NotNull
    private Integer userId;

    public @NotNull Integer getUserId() {
        return userId;
    }

    public void setUserId(@NotNull Integer userId) {
        this.userId = userId;
    }

    public BankAccountDTO(){}

    public BankAccountDTO(String bankAccountDefault) {
        this.bankAccountDefault = bankAccountDefault;
    }

    public String getBankAccountDefault() {
        return bankAccountDefault;
    }

    public void setBankAccountDefault(String bankAccountDefault) {
        this.bankAccountDefault = bankAccountDefault;
    }
}
