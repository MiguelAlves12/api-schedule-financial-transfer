package com.example.apischedulefinancialtransfer.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "`bank_account`")
public class BankAccountModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String bankAccountDefault;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserModel userModel;

    @JsonBackReference
    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBankAccountDefault() {
        return bankAccountDefault;
    }

    public void setBankAccountDefault(String bankAccountDefault) {
        this.bankAccountDefault = bankAccountDefault;
    }
}
