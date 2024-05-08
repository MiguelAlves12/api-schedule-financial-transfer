package com.example.apischedulefinancialtransfer.dtos;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

public class BankTransferDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull
    private String destinationBankAccount;
    @NotNull
    private Double transferValue;
    @NotNull
    private LocalDate transferDate;

    public BankTransferDTO(String destinationBankAccount, Double value, LocalDate transferDate) {
        this.destinationBankAccount = destinationBankAccount;
        this.transferValue = value;
        this.transferDate = transferDate;
    }

    public String getDestinationBankAccount() {
        return destinationBankAccount;
    }

    public void setDestinationBankAccount(String destinationBankAccount) {
        this.destinationBankAccount = destinationBankAccount;
    }

    public Double getTransferValue() {
        return transferValue;
    }

    public void setTransferValue(Double value) {
        this.transferValue = value;
    }

    public LocalDate getTransferDate() {
        return transferDate;
    }

    public void setTransferDate(LocalDate transferDate) {
        this.transferDate = transferDate;
    }
}
