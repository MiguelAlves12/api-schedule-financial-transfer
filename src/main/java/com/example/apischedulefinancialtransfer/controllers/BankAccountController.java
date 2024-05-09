package com.example.apischedulefinancialtransfer.controllers;

import com.example.apischedulefinancialtransfer.dtos.BankAccountDTO;
import com.example.apischedulefinancialtransfer.models.BankAccountModel;
import com.example.apischedulefinancialtransfer.services.BankAccountService;
import com.example.apischedulefinancialtransfer.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("bank-account")
@CrossOrigin("*")
public class BankAccountController {

    private final BankAccountService bankAccountService;

    public BankAccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @PostMapping("/save")
    public ResponseEntity<Object> saveBankAccount(@RequestBody @Valid BankAccountDTO bankAccountDTO){
        return bankAccountService.saveBankAccount(bankAccountDTO);
    }

    @GetMapping()
    public ResponseEntity<List<BankAccountModel>> getAllBankAccounts(){
        return bankAccountService.getAllBankAccounts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneBankAccount(@PathVariable(value="id") Integer id){
        return bankAccountService.getOneBankAccount(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateBankAccount(@PathVariable(value="id") Integer id, @RequestBody @Valid BankAccountDTO bankAccountDTO){
        return bankAccountService.updateBankAccount(id, bankAccountDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteBankAccount(@PathVariable(value="id") Integer id){
        return bankAccountService.deleteBankAccount(id);
    }
}
