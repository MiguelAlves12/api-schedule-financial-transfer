package com.example.apischedulefinancialtransfer.models;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "`bank_transfer`")
public class BankTransferModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String sourceBankAccount;
    private String destinationBankAccount;
    private Double transferValue;
    private Double transferFee;
    private LocalDate transferDate;
    private final LocalDate schedulingDate = LocalDate.now();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSourceBankAccount() {
        return sourceBankAccount;
    }

    public void setSourceBankAccount(String sourceBankAccount) {
        this.sourceBankAccount = sourceBankAccount;
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

    public void setTransferValue(Double transferValue) {
        this.transferValue = transferValue;
    }

    public Double getTransferFee() {
        return transferFee;
    }

    public void setTransferFee(Double transferFee) {
        this.transferFee = transferFee;
    }

    public LocalDate getTransferDate() {
        return transferDate;
    }

    public void setTransferDate(LocalDate transferDate) {
        this.transferDate = transferDate;
    }

    public LocalDate getSchedulingDate() {
        return schedulingDate;
    }
}
